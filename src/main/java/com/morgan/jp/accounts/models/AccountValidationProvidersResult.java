package com.morgan.jp.accounts.models;

import lombok.Data;

@Data
public class AccountValidationProvidersResult {

    private String provider;
    private boolean valid;
}
