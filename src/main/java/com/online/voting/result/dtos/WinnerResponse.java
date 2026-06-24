package com.online.voting.result.dtos;

import java.util.UUID;

public class WinnerResponse {

    private UUID candidateId;
    private long voteCount;

    public WinnerResponse() {
    }

    public WinnerResponse(UUID candidateId, long voteCount) {
        this.candidateId = candidateId;
        this.voteCount = voteCount;
    }

    public UUID getCandidateId() {
        return this.candidateId;
    }

    public void setCandidateId(UUID candidateId) {
        this.candidateId = candidateId;
    }

    public long getVoteCount() {
        return this.voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

}
