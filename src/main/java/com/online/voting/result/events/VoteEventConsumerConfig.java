package com.online.voting.result.events;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.online.voting.events.voting.VoteEvent;
import com.online.voting.result.service.ResultService;

@Configuration
public class VoteEventConsumerConfig {

    @Bean
    public Consumer<VoteEvent> voteCasted(
            ResultService resultService) {

        return event -> {

            resultService.processVote(event);
        };
    }
}