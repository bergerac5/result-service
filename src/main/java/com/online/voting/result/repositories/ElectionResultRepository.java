package com.online.voting.result.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.voting.result.models.ElectionResult;

@Repository
public interface ElectionResultRepository extends JpaRepository<ElectionResult, UUID> {

        // 📊 Get all results for an election
        List<ElectionResult> findByElectionId(UUID electionId);

        // 🏆 Get winner for a position
        Optional<ElectionResult> findTopByElectionIdAndPositionIdOrderByFinalVoteCountDesc(
                        UUID electionId,
                        UUID positionId);

        // 📊 Get all results for a position (ranking)
        List<ElectionResult> findByElectionIdAndPositionIdOrderByFinalVoteCountDesc(
                        UUID electionId,
                        UUID positionId);

        // 🔍 Check if results already finalized
        boolean existsByElectionId(UUID electionId);

        // 🏆 Get all winners for an election
        List<ElectionResult> findByElectionIdAndWinnerTrue(UUID electionId);

        // 🏆 Get winner for a position
        Optional<ElectionResult> findByElectionIdAndPositionIdAndWinnerTrue(UUID electionId,
                        UUID positionId);
}
