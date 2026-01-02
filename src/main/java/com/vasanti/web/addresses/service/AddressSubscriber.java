package com.vasanti.web.addresses.service;

import brave.Span;
import brave.Tracer;
import com.vasanti.web.addresses.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class AddressSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressSubscriber.class);

    private final Queue queue;

    private final Queue queueRequest;

    private final AddressService addressService;

    private final Tracer tracer;



    @Autowired
    public AddressSubscriber(Queue queue,
                             @Qualifier("queueRequest") Queue queueRequest,
                             AddressService addressService,
                             Tracer tracer
            ) {
        this.queue = queue;
        this.queueRequest = queueRequest;
        this.addressService = addressService;
        this.tracer = tracer;
    }

    @RabbitListener(queues = "#{queue.getName()}")	
    public void receive(final String message) {
        LOGGER.info("Listening messages from the queue!! {}" ,"#{queue.getName()");
        LOGGER.info("Received the following message from the queue= " + message);
        LOGGER.info("Message received successfully from the queue.");
    }

    /** Custom method level Span used here **/
    @RabbitListener(queues = "#{queueRequest.getName()}")
    public void processRequest(Address addr) {
        LOGGER.info("Listening messages from the queue {}" ,queueRequest.getName());
        LOGGER.info("Received the following message from the request queue \n " + addr + "");
        try {
            LOGGER.info("Inserting data into the database and adding span");
            Span saveAddressSpan = tracer.nextSpan().name("saveAddressSpan").start();
            try (Tracer.SpanInScope ws = tracer.withSpanInScope(saveAddressSpan.start())) {
                Mono<Address> resAddr = addressService.insert(addr);
                LOGGER.info("Response from  database {}",resAddr.subscribe(value -> LOGGER.info("Record inserted into database : \n " + value)));
                LOGGER.info("Inside saveAddressSpan tracer");
            } finally {
                saveAddressSpan.finish();
            }

        } catch (Exception ex) {
           LOGGER.error("Error inserting data into database",ex.getCause());
        }

    }
}
