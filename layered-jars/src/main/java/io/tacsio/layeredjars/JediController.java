package io.tacsio.layeredjars;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JediController {

	@GetMapping("/{name}")
	public String greeting(@PathVariable String name) {

		String msg = String.format("May the Force by with you, %s", StringUtils.capitalize(name));

		return msg;
	}
}