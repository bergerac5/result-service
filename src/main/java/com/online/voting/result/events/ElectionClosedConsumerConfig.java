package com.online.voting.result.events;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.online.voting.events.election.ElectionClosedEvent;
import com.online.voting.result.service.SnapshotService;

@Configuration
public class ElectionClosedConsumerConfig {

    @Bean
    public Consumer<ElectionClosedEvent> electionClosed(
            SnapshotService snapshotService) {

        return event -> {

            snapshotService.generateSnapshot(
                    event.getElectionId());
        };
    }
}