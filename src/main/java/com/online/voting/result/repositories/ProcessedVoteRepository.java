package com.online.voting.result.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online.voting.result.models.ProcessedVote;

@Repository
public interface ProcessedVoteRepository extends JpaRepository<ProcessedVote, UUID> {

    boolean existsByVoteId(UUID voteId);
}
