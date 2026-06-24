package com.online.voting.result.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.voting.result.models.ElectionResult;
import com.online.voting.result.service.ElectionResultService;

@RestController
@RequestMapping("/final-results")
public class ElectionResultController {

    private final ElectionResultService electionResultService;

    public ElectionResultController(
            ElectionResultService electionResultService) {

        this.electionResultService = electionResultService;
    }

    // 🧊 all final results
    @GetMapping("/{electionId}")
    public List<ElectionResult> getFinalResults(
            @PathVariable UUID electionId) {

        return electionResultService
                .getFinalResults(electionId);
    }

    // 🏆 winners only
    @GetMapping("/{electionId}/winners")
    public List<ElectionResult> getWinners(
            @PathVariable UUID electionId) {

        return electionResultService
                .getWinners(electionId);
    }

    // 🏆 winner for position
    @GetMapping("/{electionId}/positions/{positionId}/winner")
    public ElectionResult getWinner(
            @PathVariable UUID electionId,
            @PathVariable UUID positionId) {

        return electionResultService
                .getWinner(
                        electionId,
                        positionId);
    }
}
