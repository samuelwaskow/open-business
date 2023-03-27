package com.openbusiness.orderservice.controller;

import com.openbusiness.orderservice.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
@RequiredArgsConstructor
public class OrderController {
    private final StreamBridge streamBridge;

    @PostMapping(value = "publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        this.streamBridge.send("java_topic", MessageBuilder.withPayload(new MessageDTO(message)).build());
    }
}
