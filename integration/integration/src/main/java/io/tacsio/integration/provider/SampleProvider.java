package io.tacsio.integration.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.expression.ValueExpression;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageChannel;

@Configuration
public class SampleProvider implements ApiProvider {

    @Bean
    @Override
    public MessageChannel response() {
        return MessageChannels.direct().getObject();
    }

    @Bean
    @Override
    public HttpRequestExecutingMessageHandler messageHandler(MessageChannel request) {
        return Http.outboundGateway("http://localhost:8080/api/sample")
//                .httpMethod(HttpMethod.GET)
                .httpMethodFunction(message -> {
                    message.getHeaders().get("headers.httpMethod");
                    return null;
                })
                .expectedResponseType(String.class)
                .getObject();
    }
}
