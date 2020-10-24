package cz.upce.vetalmael;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VetAlmaelApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetAlmaelApplication.class, args);
    }
    
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

}
