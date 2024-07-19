package inha.edu.upcyclingapp.config;

import inha.edu.upcyclingapp.model.ai.bedrock.BedrockSonnetModel;
import inha.edu.upcyclingapp.model.ai.bedrock.BedrockApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;

@Configuration
public class AiConfig {

    @Value("${ai.aws.bedrock.region}")
    private String bedrockRegion;

    private BedrockRuntimeClient bedrockRuntimeClient() {
        return BedrockRuntimeClient.builder().region(Region.of(bedrockRegion)).build();
    }

    private BedrockApi bedrockApi() {
        return new BedrockApi(bedrockRuntimeClient());
    }

    @Bean
    public BedrockSonnetModel bedrockSonnetModel() {
        return new BedrockSonnetModel(bedrockApi());
    }
}