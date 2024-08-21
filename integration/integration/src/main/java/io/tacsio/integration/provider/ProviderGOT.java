package io.tacsio.integration.provider;

import io.tacsio.integration.controler.ApiRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(value = "api.provider.name", havingValue= "got")
@Component
public class ProviderGOT implements ApiProvider {

    @Bean
    @Override
    public MessageChannel response() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    @Override
    public HttpRequestExecutingMessageHandler messageHandler() {
        return Http.outboundGateway("http://localhost:8080/api/got")
                .httpMethodFunction(message -> {
                    var apiRequest = (ApiRequest) message.getPayload();
                    System.out.println(apiRequest.method());
                    return apiRequest.method();
                })
                .expectedResponseType(String.class)
                .getObject();
    }
}


