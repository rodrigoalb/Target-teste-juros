package com.challenge.interest.controller;

import com.challenge.interest.dto.JurosRequest;
import com.challenge.interest.dto.JurosResponse;
import com.challenge.interest.service.JurosService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/juros")
public class JurosController {

    private final JurosService jurosService;

    public JurosController(JurosService jurosService) {
        this.jurosService = jurosService;
    }

    @PostMapping
    public JurosResponse calcular(@Valid @RequestBody JurosRequest request) {
        return jurosService.calcular(request);
    }
}
