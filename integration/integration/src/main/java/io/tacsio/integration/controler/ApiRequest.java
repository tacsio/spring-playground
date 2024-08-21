package io.tacsio.integration.controler;

import java.util.Optional;

public record ApiRequest(String method, Optional<?> params, Object payload) {
}
