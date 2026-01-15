package com.manven.notifications.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE = "notifications.exchange";
    public static final String QUEUE = "notifications.queue";
    public static final String DLQ = "notifications.dlq";
    public static final String ROUTING_KEY = "notifications.created";
    public static final String DLQ_ROUTING_KEY = "notifications.created.dlq";

    @Bean
    DirectExchange notificationsExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Queue notificationsQueue() {
        return QueueBuilder.durable(QUEUE)
            .withArgument("x-dead-letter-exchange", EXCHANGE)
            .withArgument("x-dead-letter-routing-key", DLQ_ROUTING_KEY)
            .build();
    }

    @Bean
    Queue notificationsDlq() {
        return QueueBuilder.durable(DLQ).build();
    }

    @Bean
    Binding notificationsBinding(@Qualifier("notificationsQueue") Queue notificationsQueue,
                                 DirectExchange notificationsExchange) {
        return BindingBuilder.bind(notificationsQueue).to(notificationsExchange).with(ROUTING_KEY);
    }

    @Bean
    Binding notificationsDlqBinding(@Qualifier("notificationsDlq") Queue notificationsDlq,
                                    DirectExchange notificationsExchange) {
        return BindingBuilder.bind(notificationsDlq).to(notificationsExchange).with(DLQ_ROUTING_KEY);
    }

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
