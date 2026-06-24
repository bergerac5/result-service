package com.online.voting.result.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "election_results")

public class ElectionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID electionId;
    private UUID positionId;
    private UUID candidateId;

    private long finalVoteCount;

    private boolean winner;

    public ElectionResult() {
    }

    public ElectionResult(UUID id, UUID electionId, UUID positionId, UUID candidateId, long finalVoteCount,
            boolean winner) {
        this.id = id;
        this.electionId = electionId;
        this.positionId = positionId;
        this.candidateId = candidateId;
        this.finalVoteCount = finalVoteCount;
        this.winner = winner;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getElectionId() {
        return this.electionId;
    }

    public void setElectionId(UUID electionId) {
        this.electionId = electionId;
    }

    public UUID getPositionId() {
        return this.positionId;
    }

    public void setPositionId(UUID positionId) {
        this.positionId = positionId;
    }

    public UUID getCandidateId() {
        return this.candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public long getFinalVoteCount() {
        return this.finalVoteCount;
    }

    public void setFinalVoteCount(long finalVoteCount) {
        this.finalVoteCount = finalVoteCount;
    }

    public boolean isWinner() {
        return this.winner;
    }

    public boolean getWinner() {
        return this.winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

}