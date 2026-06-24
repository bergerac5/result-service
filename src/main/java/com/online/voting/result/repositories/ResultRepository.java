package com.online.voting.result.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.voting.result.models.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, UUID> {

    // Find specific candidate result
    Optional<Result> findByElectionIdAndPositionIdAndCandidateId(
            UUID electionId,
            UUID positionId,
            UUID candidateId);

    // Get all results for an election
    List<Result> findByElectionId(UUID electionId);

    // Get results per position (sorted for ranking)
    List<Result> findByElectionIdAndPositionIdOrderByVoteCountDesc(
            UUID electionId,
            UUID positionId);

    // Get winner (top candidate)
    Optional<Result> findTopByElectionIdAndPositionIdOrderByVoteCountDesc(
            UUID electionId,
            UUID positionId);
}
