package peaksoft.house.gadgetariumb9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class GadgetariumTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(GadgetariumTestApplication.class, args);
        System.out.println("Welcome to Java-9's Gadgetarium project!");
    }

    @GetMapping
    public String greetings() {
        return "index";
    }
}
