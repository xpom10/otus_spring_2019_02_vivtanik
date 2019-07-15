package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.RendezvousChannel;
import org.springframework.integration.dsl.channel.MessageChannels;

@Configuration
public class IntegrationConfig {

    @Bean
    public RendezvousChannel channel1() {
        return MessageChannels.rendezvous("channel_1").get();
    }

    @Bean
    public RendezvousChannel channel2() {
        return MessageChannels.rendezvous("channel_2").get();
    }

}
