package ru.otus.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    public DirectChannel channel1() {
        return MessageChannels.direct("channel_1").get();
    }

    @Bean
    public DirectChannel channel2() {
        return MessageChannels.direct("channel_2").get();
    }

    @Bean
    public IntegrationFlow saveBookFlow() {
        return IntegrationFlows
                .from("channel1")
                .handle("libraryServiceImpl", "saveBook")
                .channel("channel2")
                .get();
    }

}
