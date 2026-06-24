package com.online.voting.result.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "processed_votes")

public class ProcessedVote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID voteId;

    private LocalDateTime processedAt;

    public ProcessedVote() {
    }

    public ProcessedVote(UUID voteId, LocalDateTime processedAt) {
        this.voteId = voteId;
        this.processedAt = processedAt;
    }

    public UUID getVoteId() {
        return this.voteId;
    }

    public void setVoteId(UUID voteId) {
        this.voteId = voteId;
    }

    public LocalDateTime getProcessedAt() {
        return this.processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

}