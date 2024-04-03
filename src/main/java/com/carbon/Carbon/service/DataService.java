package com.carbon.Carbon.service;
import com.carbon.Carbon.model.ApiResponse;
import com.carbon.Carbon.model.DataEntries;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataService {
    private static final String API_URL = "https://api.publicapis.org/entries";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public DataEntries[] fetchApiEntries(String category, Integer limit) {
        // Build the request parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_URL);
        if (category != null) {
            builder.queryParam("category", category);
        }

        // Make the API request
        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                String responseBody = response.getBody();
                // Deserialize the JSON response into a wrapper object containing an array of DataEntries
                ApiResponse apiResponseWrapper = objectMapper.readValue(responseBody, ApiResponse.class);

                // Get the entries from the wrapper object
                DataEntries[] entries = apiResponseWrapper.getEntries();

                // Apply the limit if provided
                if (limit != null && limit > 0 && entries != null) {
                    entries = Arrays.copyOf(entries, Math.min(limit, entries.length));
                }

                return entries;
            } catch (JsonProcessingException e) {
                // Handle JSON processing exception
                e.printStackTrace();
            }
        } else {
            // Handle non-successful HTTP response
            System.err.println("Error: " + response.getStatusCodeValue() + " - " + response.getBody());
        }

        return null;
    }
}
