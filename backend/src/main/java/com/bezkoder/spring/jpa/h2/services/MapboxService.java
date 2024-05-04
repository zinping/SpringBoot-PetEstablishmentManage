package com.bezkoder.spring.jpa.h2.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapboxService {

    @Value("${mapbox.api.token}")
    private String mapboxApiToken;

    public String findAddress(String query) {
        String url = "https://api.mapbox.com/geocoding/v5/mapbox.places/" + query + ".json?access_token=" + mapboxApiToken;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(" -------------- " + response);
        ;

        return response;
    }
}
