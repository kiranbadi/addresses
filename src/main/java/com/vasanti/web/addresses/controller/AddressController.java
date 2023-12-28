package com.vasanti.web.addresses.controller;


import brave.Span;
import brave.Tracer;
import com.vasanti.web.addresses.model.ResponseModel;
import com.vasanti.web.addresses.model.address;
import com.vasanti.web.addresses.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;



@RestController
@RequestMapping(path = "/address")
@AllArgsConstructor
@Slf4j
public class AddressController {


    private final AddressService addressService;

    private final RabbitTemplate rabbitTemplate;

    private final Binding binding;

    private final Binding requestBinding;

    private final Tracer tracer;






    @NewSpan
    public Mono<ServerResponse> TestSetUp() {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("Reactive Spring Service Works"));
    }

     /** Default Span used here **/
    @NewSpan
    @PostMapping("/save")
    @ResponseBody
    public Mono<ResponseModel> insertAddress(@RequestBody address addr) {
        Mono<address> adr =  addressService.insert(addr);
        return TransformAddress(adr);
    }

  /*Added a manual trace and span to measure response. Annotation does not work  */
    public Mono<ResponseModel> TransformAddress(Mono<address>adr){
        ResponseModel model  = new ResponseModel();
        Span returnResponseModel = tracer.nextSpan().name("returnResponseModel").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(returnResponseModel.start())) {
            /* non-blocking subscriber */
            Mono.just(adr).thenReturn(model).subscribe(
                    value -> {
                        model.setResponseMessage("Address saved Successfully");
                        model.setRequestAcknowledgementId(generateRandomId());
                        model.setErrorMessage("No Errors");
                    }).dispose();
        } finally {
            returnResponseModel.finish();
        }
        return Mono.just(model);
    }

    // TODO : Save the reference number to database for support
    public static String generateRandomId() {
        return java.util.UUID.randomUUID().toString().replaceAll("-","");
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<address> getAddress(@PathVariable String id) {
        return addressService.findById(id);
    }

    /** Default Span used here **/
    @GetMapping("/find/{addressid}")
    @ResponseBody
    @NewSpan
    public Mono<address> getAddress(@PathVariable Long addressid) {
        return addressService.findByaddressid(addressid);
    }


    @GetMapping("/alladdresses")
    @ResponseBody
    @NewSpan
    public Flux<address> getAllAddress() {
           return addressService.findAll();
    }

    @GetMapping("/all/{state}/{page}/{size}")
    @ResponseBody
    @NewSpan
    public Flux<address> FindAllAddress(@PathVariable String state,
                                        @PathVariable int page,
                                        @PathVariable int size) {
        log.info("page is {}, size is {} ,state is {} ",page,size,state);
        Pageable paging = PageRequest.of(page, size);
        return addressService.findAllByState(state,paging);
    }

    @GetMapping("/findall/{city}")
    @ResponseBody
    @NewSpan
    public Flux<address> FindAllByCityCtrl(@PathVariable String city,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return addressService.findAllByCity(city,pageable);
    }

    //TODO: POST MAPPING NEEDS TO RETURN VALID MESSAGE NOT ALL LIST
    @PostMapping("/bulksave")
    @ResponseBody
    public Flux<address> saveall(@RequestBody List<address> addresses) {
        return addressService.saveAll(addresses);
    }

    @GetMapping(value = "/send/{msg}")
    @ResponseStatus(code = HttpStatus.OK)
    public String send(@PathVariable("msg") final String message) {
        log.info("Sending message to the queue.");
        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), message);
        log.info("Message sent successfully to the queue, sending back the response to the user.");
        return "Message sent successfully to the queue.";
    }

    /** Default Span used here **/
    @NewSpan
    @PostMapping(value = "/amqp/create")
    public String sendAddress(@RequestBody address addr) {
        log.info("Sending message to the request queue.");
        // convert address object to json string and pass it to queue
        rabbitTemplate.convertAndSend(requestBinding.getExchange(), requestBinding.getRoutingKey(), addr);
        log.info("Message sent successfully to the request queue, sending back the response to the user.");
        //TODO : Send the async response to client and ensure the request has correlation id associated with it.
        //TODO : Send the response to response queue with correlation id associated with request.
        return "Message sent successfully to the request queue.";
    }
}
