package com.mihey.sqsaws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SqsQueueSender {

    @Value("${cloud.aws.end-point.url}")
    private String destinationName;
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @GetMapping("{message}")
    public void send(@PathVariable String message) {
        queueMessagingTemplate.send(destinationName, MessageBuilder.withPayload(message)
                .build());
    }

    @SqsListener("spring-aws-sqs")
    public void receiveMessage(String message, @Header("SenderId") String senderId) {
        System.out.println("message: '" + message + "' sent from: " + senderId);
    }
}