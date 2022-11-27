package com.morgan.jp.accounts.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class AccountValidationResponseDto {

    private final List<AccountValidationProvidersResult> providersResult;
}
