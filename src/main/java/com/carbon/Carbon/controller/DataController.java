package com.carbon.Carbon.controller;

import com.carbon.Carbon.model.DataEntries;
import com.carbon.Carbon.service.DataService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataController {
    @Autowired
    DataService dataService;

    @GetMapping
    public DataEntries[] getApiEntries(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer limit) {
        return dataService.fetchApiEntries(category, limit);
    }
    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

}
