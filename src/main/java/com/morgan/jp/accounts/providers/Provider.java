package com.morgan.jp.accounts.providers;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Provider {

    public String name;
    public String url;

}
