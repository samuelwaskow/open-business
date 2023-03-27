package com.openbusiness.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class ConsumerService {

    @Bean
    public Consumer<String> receiveMessage() {
        return message -> log.info("Consuming [{}]", message);
    }

}
