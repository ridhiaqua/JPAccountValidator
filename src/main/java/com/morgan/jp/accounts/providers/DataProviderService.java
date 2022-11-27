package com.morgan.jp.accounts.providers;

import com.morgan.jp.accounts.models.AccountValidationProvidersResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataProviderService implements IDataProviderService {

    private final DataProviders dataProviders;

    @Override
    public List<AccountValidationProvidersResult> validateAccount(String accountNumber, List<String> dataProviders) {
        List<AccountValidationProvidersResult> resultList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(dataProviders)) {
            for (String provider : dataProviders) {
                resultList.add(getProviderResults(accountNumber, provider));
            }
            return resultList;
        }

        return defaultProvidersResult(accountNumber);
    }

    private List<AccountValidationProvidersResult> defaultProvidersResult(String accountNumber) {
        List<AccountValidationProvidersResult> resultList = new ArrayList<>();
        Map<String, Provider> defaultProviderMap = this.dataProviders.getProviders();
        for (String provider : defaultProviderMap.keySet()) {
            resultList.add(getProviderResults(accountNumber, provider));
        }
        return resultList;
    }

    private AccountValidationProvidersResult getProviderResults(String accountNumber, String provider) {
        AccountValidationProvidersResult result = new AccountValidationProvidersResult();
        result.setProvider(provider);
        result.setValid(validateAccountUsingProvider(accountNumber, provider));
        return result;
    }

    private boolean validateAccountUsingProvider(String accountNumber, String dataProvider) {
        log.info("Calling data provider " + dataProvider + " for account no: " + accountNumber);
        // here we just compare if the provider in request is in our default providers list
        // otherwise this could be http call to the provider endpoint
        return this.dataProviders.getProviders().containsKey(dataProvider);
    }
}
