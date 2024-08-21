package io.tacsio.integration.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.http.HttpHeaders;
import org.springframework.integration.http.outbound.HttpRequestExecutingMessageHandler;
import org.springframework.messaging.MessageChannel;

@EnableIntegration
@Configuration
public class IntegrationConfig {

    private final Logger log = LoggerFactory.getLogger(IntegrationConfig.class);

    @Bean
    MessageChannel request() {
        return MessageChannels.direct().getObject();
    }


    @Bean
    IntegrationFlow flow(MessageChannel response, HttpRequestExecutingMessageHandler messageHandler) {
        return IntegrationFlow
                .from(request())
//                .enrichHeaders(spec -> spec.headerFunction(HttpHeaders.REQUEST_METHOD, message -> {
//                    messageHandler.setHttpMethod((HttpMethod) message.getHeaders().get("HTTP_METHOD"));
//                    return spec;
//                }))
                .handle(messageHandler)
                .channel(response)
                .get();
    }
}
