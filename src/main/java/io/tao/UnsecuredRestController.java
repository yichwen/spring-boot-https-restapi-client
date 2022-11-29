package io.tao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class UnsecuredRestController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    @Qualifier("WebClient")
    private WebClient webClient;
    @Autowired
    @Qualifier("CertWebClient")
    private WebClient certWebClient;

    @GetMapping("/resttemplate")
    public String restTemplate() {
        ResponseEntity<String> response = restTemplate.getForEntity("https://localhost:12000/secured", String.class);
        return response.getBody();
    }

    @GetMapping("/webclient")
    public Mono<String> webClient() {
        return webClient.get().uri("https://localhost:12000/secured").retrieve().bodyToMono(String.class);
    }

    @GetMapping("/certwebclient")
    public Mono<String> certWebClient() {
        return certWebClient.get().uri("https://localhost:12000/secured").retrieve().bodyToMono(String.class);
    }
    
}
