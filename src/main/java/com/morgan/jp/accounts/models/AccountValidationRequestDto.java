package com.morgan.jp.accounts.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountValidationRequestDto {

    public String accountNumber;
    public List<String> providers;
}
