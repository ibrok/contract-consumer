package com.contract.example.consumer.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BeerClient {

    private RestTemplate restTemplate = new RestTemplate();

    public BeerMenu getBeerMenu() {
        return restTemplate.getForObject("http://localhost:8080/beer", BeerMenu.class);
    }

    public BeerMenu getBeerMenu(BeerType type) {
        return restTemplate.getForObject("http://localhost:8080/beer/" + type.name(), BeerMenu.class);
    }

    public String order(Order order) {
        return restTemplate.postForObject("http://localhost:8080/beer", order, String.class);
    }

}
