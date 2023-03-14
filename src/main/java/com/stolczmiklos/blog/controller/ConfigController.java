package com.stolczmiklos.blog.controller;

import com.stolczmiklos.blog.dto.ConfigRequest;
import com.stolczmiklos.blog.service.ConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @GetMapping("/myConfig")
    public ResponseEntity<ConfigRequest> getConfig() {
        ConfigRequest configRequest = configService.getConfig();

        return new ResponseEntity<>(configRequest, HttpStatus.OK);
    }

    @GetMapping("/default")
    public ResponseEntity<ConfigRequest> getDefaultConfig() {
        ConfigRequest configRequest = configService.getDefaultConfig();
        return new ResponseEntity<>(configRequest, HttpStatus.OK);
    }

    @PostMapping("/myConfig")
    public ResponseEntity<?> saveConfig(@RequestBody ConfigRequest configRequest) {
        configService.save(configRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
