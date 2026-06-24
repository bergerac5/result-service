package com.online.voting.result.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.online.voting.result.handler.ResourceNotFoundException;
import com.online.voting.result.models.ElectionResult;
import com.online.voting.result.repositories.ElectionResultRepository;

@Service
public class ElectionResultService {

        private final ElectionResultRepository repository;

        public ElectionResultService(
                        ElectionResultRepository repository) {

                this.repository = repository;
        }

        public List<ElectionResult> getFinalResults(
                        UUID electionId) {

                List<ElectionResult> results = repository.findByElectionId(
                                electionId);

                if (results.isEmpty()) {

                        throw new ResourceNotFoundException(
                                        "Final results not found for election: "
                                                        + electionId);
                }

                return results;
        }

        public List<ElectionResult> getWinners(
                        UUID electionId) {

                List<ElectionResult> winners = repository.findByElectionIdAndWinnerTrue(
                                electionId);

                if (winners.isEmpty()) {

                        throw new ResourceNotFoundException(
                                        "No winners found for election: "
                                                        + electionId);
                }

                return winners;
        }

        public ElectionResult getWinner(
                        UUID electionId,
                        UUID positionId) {

                return repository
                                .findByElectionIdAndPositionIdAndWinnerTrue(
                                                electionId,
                                                positionId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Winner not found for the specified position"));
        }
}