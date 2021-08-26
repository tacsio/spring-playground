package io.tacsio.envdocker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EnvdockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnvdockerApplication.class, args);
	}

}

@RestController
class EnvController {

	@Value("${FROM_START}")
	String fromStart;
	@Value("${FROM_DOCKERFILE}")
	String fromDocker;
	@Value("${FROM_KUBERNETES}")
	String fromKubernetes;

	@GetMapping
	public ResponseEntity envs() {

		Map<String, String> envs = new HashMap<>();
		envs.put("start", fromStart);
		envs.put("docker", fromDocker);
		envs.put("kubernetes", fromKubernetes);

		return ResponseEntity.ok(envs);
	}

}
