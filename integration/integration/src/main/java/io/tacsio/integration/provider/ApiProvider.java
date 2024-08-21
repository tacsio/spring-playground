package io.tacsio.integration.provider;

import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageChannel;

public interface ApiProvider {
    MessageChannel response();

    HttpRequestExecutingMessageHandler messageHandler(MessageChannel getRequest);

}
