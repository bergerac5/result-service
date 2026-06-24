package com.online.voting.result.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.voting.events.voting.VoteEvent;
import com.online.voting.result.models.ProcessedVote;
import com.online.voting.result.models.Result;
import com.online.voting.result.repositories.ProcessedVoteRepository;
import com.online.voting.result.repositories.ResultRepository;

@Service
public class ResultService {

        private final ResultRepository resultRepository;
        private final ProcessedVoteRepository processedVoteRepository;

        public ResultService(
                        ResultRepository resultRepository,
                        ProcessedVoteRepository processedVoteRepository) {

                this.resultRepository = resultRepository;
                this.processedVoteRepository = processedVoteRepository;
        }

        @Transactional
        public void processVote(VoteEvent event) {

                // 🔒 idempotency + race-condition protection
                try {

                        ProcessedVote processedVote = new ProcessedVote();

                        processedVote.setVoteId(
                                        event.getVoteId());

                        processedVote.setProcessedAt(
                                        LocalDateTime.now());

                        processedVoteRepository.save(
                                        processedVote);

                } catch (DataIntegrityViolationException ex) {

                        // duplicate event already processed
                        return;
                }

                Result result = resultRepository
                                .findByElectionIdAndPositionIdAndCandidateId(
                                                event.getElectionId(),
                                                event.getPositionId(),
                                                event.getCandidateId())
                                .orElseGet(() -> {

                                        Result r = new Result();

                                        r.setElectionId(
                                                        event.getElectionId());

                                        r.setPositionId(
                                                        event.getPositionId());

                                        r.setCandidateId(
                                                        event.getCandidateId());

                                        r.setVoteCount(0);

                                        return r;
                                });

                result.setVoteCount(
                                result.getVoteCount() + 1);

                resultRepository.save(result);
        }

        public List<Result> getElectionResults(
                        UUID electionId) {

                return resultRepository
                                .findByElectionId(electionId);
        }

        public List<Result> getPositionResults(
                        UUID electionId,
                        UUID positionId) {

                return resultRepository
                                .findByElectionIdAndPositionIdOrderByVoteCountDesc(
                                                electionId,
                                                positionId);
        }
}