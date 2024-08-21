package io.tacsio.integration.controler;

import io.tacsio.integration.service.IntegrationService;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdapterController {

    private final IntegrationService integrationService;

    public AdapterController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping("/proxy/sample")
    public String forwardRequest() {
        var headers = new MessageHeaders(Map.of("HTTP_METHOD", HttpMethod.GET));

//        return integrationService.send(MessageBuilder.createMessage("payload", headers));
        return integrationService.send("Payload get");
    }

    @PostMapping("/proxy/sample")
    public String forwardPost() {

        var headers = new MessageHeaders(Map.of("HTTP_METHOD", HttpMethod.POST));

        return integrationService.send("Payload post");
    }

}
