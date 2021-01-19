package com.vasanti.web.addresses.controller;



import brave.Span;
import brave.Tracer;
import com.vasanti.web.addresses.model.ResponseModel;
import com.vasanti.web.addresses.model.address;
import com.vasanti.web.addresses.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;



@RestController
@RequestMapping(path = "/address")
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);


    private final AddressService addressService;

    private final RabbitTemplate rabbitTemplate;

    private final Binding binding;

    private final Binding requestBinding;

    private final Binding responseBinding;

    private static Tracer tracer;




    @Autowired
    public AddressController(AddressService addressService,
                             RabbitTemplate rabbitTemplate,
                             Binding binding,
                             Binding requestBinding,
                             Binding responseBinding,
                             Tracer tracer


    )
    {
        this.addressService = addressService;
        this.rabbitTemplate = rabbitTemplate;
        this.binding = binding;
        this.requestBinding = requestBinding;
        this.responseBinding = responseBinding;
        this.tracer = tracer;
    }


    @NewSpan
    public Mono<ServerResponse> TestSetUp(ServerRequest request) {
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
    public static Mono<ResponseModel> TransformAddress(Mono<address>adr){
        ResponseModel model  = new ResponseModel();
        Span returnResponseModel = tracer.nextSpan().name("returnResponseModel").start();
        try (Tracer.SpanInScope ws = tracer.withSpanInScope(returnResponseModel.start())) {
            Mono.just(adr).thenReturn(model).subscribe(
                    value -> {
                        if (adr == null) {
                            model.setResponseMessage("Address saving failed.Try Again");
                            model.setRequestAcknowledgementId(generateRandomId());
                            model.setErrorMessage("Some Error has occurred.Try again");
                        } else {
                            model.setResponseMessage("Address saved Successfully");
                            model.setRequestAcknowledgementId(generateRandomId());
                            model.setErrorMessage("No Errors");
                        }
                    });
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
        LOGGER.info("page is {}, size is {} ,state is {} ",page,size,state);
        Pageable paging = PageRequest.of(page, size);
        Flux<address> totalPages = addressService.findAllByState(state,paging);
        return totalPages;
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
        LOGGER.info("Sending message to the queue.");
        rabbitTemplate.convertAndSend(binding.getExchange(), binding.getRoutingKey(), message);
        LOGGER.info("Message sent successfully to the queue, sending back the response to the user.");
        return "Message sent successfully to the queue.";
    }

    /** Default Span used here **/
    @NewSpan
    @PostMapping(value = "/amqp/create")
    public String sendAddress(@RequestBody address addr) {
        LOGGER.info("Sending message to the request queue.");
        // convert address object to json string and pass it to queue
        rabbitTemplate.convertAndSend(requestBinding.getExchange(), requestBinding.getRoutingKey(), addr);
        LOGGER.info("Message sent successfully to the request queue, sending back the response to the user.");
        //TODO : Send the async response to client and ensure the request has correlation id associated with it.
        //TODO : Send the response to response queue with correlation id associated with request.
        return "Message sent successfully to the request queue.";
    }
}
