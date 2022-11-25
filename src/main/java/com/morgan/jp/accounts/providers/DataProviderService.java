package com.morgan.jp.accounts.providers;

import com.morgan.jp.accounts.models.AccountValidationProvidersResult;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataProviderService implements IDataProviderService {

    private final DataProviders dataProviders;

    @Autowired
    public DataProviderService(DataProviders dataProviders) {
        this.dataProviders = dataProviders;
    }

    @Override
    public List<AccountValidationProvidersResult> validateAccount(String accountNumber, List<String> dataProvider) {
        List<AccountValidationProvidersResult> resultList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(dataProvider)) {
            for (String provider : dataProvider) {
                resultList.add(getProviderResults(accountNumber, provider));
            }
            return resultList;
        }

        return defaultProvidersResult(accountNumber);
    }

    private List<AccountValidationProvidersResult> defaultProvidersResult(String accountNumber) {
        List<AccountValidationProvidersResult> resultList = new ArrayList<>();
        for (String provider : this.dataProviders.getProviders().keySet()) {
            resultList.add(getProviderResults(accountNumber, provider));
        }
        return resultList;
    }

    private AccountValidationProvidersResult getProviderResults(String accountNumber, String provider) {
        AccountValidationProvidersResult result = new AccountValidationProvidersResult();
        result.setProvider(provider);
        result.setValid(validateAccountAgainstProvider(accountNumber, provider));
        return result;
    }

    private boolean validateAccountAgainstProvider(String accountNumber, String dataProvider) {
        // call the endpoint and checks isValid
        return this.dataProviders.getProviders().containsKey(dataProvider);
    }
}
