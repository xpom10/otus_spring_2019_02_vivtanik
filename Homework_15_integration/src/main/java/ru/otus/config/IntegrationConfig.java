package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.channel.MessageChannels;

@Configuration
public class IntegrationConfig {

    @Bean
    public DirectChannel channel1() {
        return MessageChannels.direct("channel_1").get();
    }

    @Bean
    public DirectChannel channel2() {
        return MessageChannels.direct("channel_2").get();
    }

}
