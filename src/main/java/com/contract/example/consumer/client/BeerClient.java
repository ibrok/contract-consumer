package com.contract.example.consumer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BeerClient {

    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl;

    public BeerClient(@Value("${producer.baseUrl}") String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public BeerMenu getBeerMenu() {
        HttpEntity httpEntity = new HttpEntity(createHeaders());
        return restTemplate.exchange(baseUrl +"/beer", HttpMethod.GET, httpEntity, BeerMenu.class).getBody();
    }

    public BeerMenu getBeerMenu(BeerType type) {
        HttpEntity httpEntity = new HttpEntity(createHeaders());
        return restTemplate.exchange(baseUrl +"/beer/" + type.name(), HttpMethod.GET, httpEntity, BeerMenu.class).getBody();
    }

    public String order(Order order) {
        return restTemplate.postForObject(baseUrl +"/beer", order, String.class);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
