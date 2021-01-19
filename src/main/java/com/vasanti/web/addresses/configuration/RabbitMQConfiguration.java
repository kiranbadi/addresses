package com.vasanti.web.addresses.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConfiguration.class);
    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.addresses.request.queue}")
    private String queueAddressesRequest;

    @Value("${rabbitmq.addresses.response.queue}")
    private String queueAddressesResponse;

    @Value("${rabbitmq.type.topic.exchange}")
    private String topicExchange;

    @Value("${rabbitmq.type.direct.exchange}")
    private String directExchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        LOGGER.debug("Using RabbitTemplate from Spring !!");
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, Boolean.FALSE);
    }
    @Bean
    Queue queueRequest() {
        return new Queue(queueAddressesRequest, Boolean.FALSE);
    }

    @Bean
    Queue queueResponse() {
        return new Queue(queueAddressesResponse, Boolean.FALSE);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    DirectExchange directExchange() {return new DirectExchange(directExchange);}

    @Bean
    Binding binding(final Queue queue, final TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }

    @Bean
    Binding requestBinding(final Queue queueRequest, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueRequest).to(directExchange).with(routingKey);
    }

    @Bean
    Binding responseBinding(final Queue queueResponse, final DirectExchange directExchange) {
        return BindingBuilder.bind(queueResponse).to(directExchange).with(routingKey);
    }


}
