package com.practice.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();




//        MatchingStrategies 定義了如何將來源物件（例如 DTO）的屬性與目標物件（例如實體）的屬性進行匹配。ModelMapper 提供了三種主要的匹配策略：STANDARD、STRICT 和 LOOSE。
//        STANDARD：要求屬性名稱完全匹配，適合屬性名稱一致的場景，中等靈活性，推薦作為預設選擇。
//        STRICT：要求屬性名稱和類型完全匹配，適合高精度要求的場景，但可能需要更多配置。
//        LOOSE：允許鬆散匹配，適合屬性名稱或結構差異較大的場景，但可能導致意外映射，需謹慎使用。

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper;
    }
}
