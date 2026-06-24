package com.online.voting.result.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "results", uniqueConstraints = @UniqueConstraint(columnNames = { "electionId", "positionId",
        "candidateId" }))
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID electionId;

    @Column(nullable = false)
    private UUID positionId;

    @Column(nullable = false)
    private UUID candidateId;

    @Column(nullable = false)
    private long voteCount;

    // No-args constructor
    public Result() {
    }

    // All-args constructor
    public Result(UUID id, UUID electionId, UUID positionId, UUID candidateId, long voteCount) {
        this.id = id;
        this.electionId = electionId;
        this.positionId = positionId;
        this.candidateId = candidateId;
        this.voteCount = voteCount;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getElectionId() {
        return electionId;
    }

    public void setElectionId(UUID electionId) {
        this.electionId = electionId;
    }

    public UUID getPositionId() {
        return positionId;
    }

    public void setPositionId(UUID positionId) {
        this.positionId = positionId;
    }

    public UUID getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }
}
