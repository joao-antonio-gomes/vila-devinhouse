package br.com.village.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class VillageConfig {

    @Value("${budget}")
    private Double budget;

    public Double getBudget() {
        return budget;
    }
}
