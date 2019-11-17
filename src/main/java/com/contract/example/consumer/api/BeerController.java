package com.contract.example.consumer.api;

import com.contract.example.consumer.client.BeerClient;
import com.contract.example.consumer.client.BeerMenu;
import com.contract.example.consumer.client.BeerType;
import com.contract.example.consumer.client.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("beer")
public class BeerController {

    private final BeerClient beerClient;

    public BeerController(BeerClient beerClient) {
        this.beerClient = beerClient;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BeerMenu getBeers() {
        return beerClient.getBeerMenu();
    }

    @GetMapping(value = "/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public BeerMenu getBeersOfType(@PathVariable BeerType type) {
        return beerClient.getBeerMenu(type);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String orderBeers(@RequestBody Order order) {
        return beerClient.order(order);
    }

}
