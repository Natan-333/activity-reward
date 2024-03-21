package br.com.fiap.activityrewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class ActivityrewardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityrewardsApplication.class, args);
	}

	@GetMapping
	public String home() {
		return "index";
	}

}
