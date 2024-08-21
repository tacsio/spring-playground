package io.tacsio.integration.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface IntegrationService {

    @Gateway(requestChannel = "request", replyChannel = "response")
    String send(String msg);
}
