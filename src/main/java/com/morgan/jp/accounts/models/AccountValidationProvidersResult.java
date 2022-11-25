package com.morgan.jp.accounts.models;

import lombok.Data;

@Data
public class AccountValidationProvidersResult {
    public String provider;
    public boolean valid;
}
