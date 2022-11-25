package com.morgan.jp.accounts.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountValidationResponseDto {
    private final List<AccountValidationProvidersResult> providersResult;
}
