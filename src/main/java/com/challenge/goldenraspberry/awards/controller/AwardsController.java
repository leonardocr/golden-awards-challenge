package com.challenge.goldenraspberry.awards.controller;

import com.challenge.goldenraspberry.awards.dto.AwardResultDTO;
import com.challenge.goldenraspberry.awards.services.AwardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/awards"})
public class AwardsController {
    private final AwardsService service;

    public AwardsController(AwardsService service) {
        this.service = service;
    }

    @GetMapping("/intervals")
    public ResponseEntity<AwardResultDTO> intervals() {
        AwardResultDTO result = service.getAwardIntervals();
        return ResponseEntity.ok(result);
    }
}
