package br.com.village.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class VillageConfig {

    private static Double budget;

    public VillageConfig(@Value("${budget}") Double budget) {
        VillageConfig.budget = budget;
    }

    public static Double getBudget() {
        return budget;
    }
}
