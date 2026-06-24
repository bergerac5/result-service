package com.online.voting.result.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.voting.result.models.Result;
import com.online.voting.result.service.ResultService;

@RestController
@RequestMapping("/live-results")
public class LiveResultController {

    private final ResultService resultService;

    public LiveResultController(
            ResultService resultService) {

        this.resultService = resultService;
    }

    // 📊 all live results for election
    @GetMapping("/{electionId}")
    public List<Result> getElectionResults(
            @PathVariable UUID electionId) {

        return resultService
                .getElectionResults(electionId);
    }

    // 📈 live ranking per position
    @GetMapping("/{electionId}/positions/{positionId}")
    public List<Result> getPositionResults(
            @PathVariable UUID electionId,
            @PathVariable UUID positionId) {

        return resultService
                .getPositionResults(
                        electionId,
                        positionId);
    }
}
