package com.online.voting.result.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.voting.result.models.ElectionResult;
import com.online.voting.result.models.Result;
import com.online.voting.result.repositories.ElectionResultRepository;
import com.online.voting.result.repositories.ResultRepository;

@Service
public class SnapshotService {

    private final ResultRepository resultRepository;
    private final ElectionResultRepository electionResultRepository;

    public SnapshotService(
            ResultRepository resultRepository,
            ElectionResultRepository electionResultRepository) {

        this.resultRepository = resultRepository;
        this.electionResultRepository = electionResultRepository;
    }

    @Transactional
    public void generateSnapshot(UUID electionId) {

        // 🧊 prevent duplicate snapshots
        if (electionResultRepository
                .existsByElectionId(electionId)) {
            return;
        }

        List<Result> results = resultRepository
                .findByElectionId(electionId);

        Map<UUID, List<Result>> grouped = results.stream()
                .collect(java.util.stream.Collectors
                        .groupingBy(
                                Result::getPositionId));

        List<ElectionResult> snapshots = new ArrayList<>();

        for (Map.Entry<UUID, List<Result>> entry : grouped.entrySet()) {

            List<Result> ranked = entry.getValue();

            ranked.sort((a, b) -> Long.compare(
                    b.getVoteCount(),
                    a.getVoteCount()));

            long highest = ranked.get(0).getVoteCount();

            for (Result result : ranked) {

                ElectionResult snapshot = new ElectionResult();

                snapshot.setElectionId(
                        result.getElectionId());

                snapshot.setPositionId(
                        result.getPositionId());

                snapshot.setCandidateId(
                        result.getCandidateId());

                snapshot.setFinalVoteCount(
                        result.getVoteCount());

                snapshot.setWinner(
                        result.getVoteCount() == highest);

                snapshots.add(snapshot);
            }
        }

        electionResultRepository
                .saveAll(snapshots);
    }
}
