package com.morgan.jp.accounts.providers;

import com.morgan.jp.accounts.models.AccountValidationProvidersResult;

import java.util.List;

public interface IDataProviderService {

    List<AccountValidationProvidersResult> validateAccount(String accountNumber, List<String> dataProvider);
}
