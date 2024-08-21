package io.tacsio.integration.controler;

import io.tacsio.integration.service.IntegrationService;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class AdapterController {

    private final IntegrationService integrationService;

    public AdapterController(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @GetMapping("/proxy/sample")
    public String forwardRequest() {
        var request = new ApiRequest(HttpMethod.GET.name(), Optional.empty(), "Payload post");
        return integrationService.send(request);
    }

    @PostMapping("/proxy/sample")
    public String forwardPost() {
        var request = new ApiRequest(HttpMethod.POST.name(), Optional.empty(), "Payload post");
        return integrationService.send(request);
    }

}
