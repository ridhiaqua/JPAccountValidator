package com.morgan.jp.accounts.providers;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "data")
@Data
public class DataProviders {

    public List<Provider> providers;

}
