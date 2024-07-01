package com.ruoyi.ruoyiasset.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ConstantConfig {

    @Value("${query.symbol.url}")
    private String symbolUrl ;
    @Value("${query.symbol.proxy.host}")
    private String proxyHost ;
    @Value("${query.symbol.proxy.port}")
    private Integer proxyPort ;

}
