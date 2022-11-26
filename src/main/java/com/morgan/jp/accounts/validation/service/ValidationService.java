package com.morgan.jp.accounts.validation.service;

import com.morgan.jp.accounts.models.AccountValidationProvidersResult;
import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import com.morgan.jp.accounts.models.AccountValidationResponseDto;
import com.morgan.jp.accounts.providers.IDataProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService implements IValidationService {

    private final IDataProviderService dataProviderService;

    @Autowired
    public ValidationService(IDataProviderService dataProviderService) {
        this.dataProviderService = dataProviderService;
    }

    @Override
    public AccountValidationResponseDto validateRequest(AccountValidationRequestDto accountValidationRequestDto) {
        List<AccountValidationProvidersResult> result = dataProviderService.validateAccount(
                accountValidationRequestDto.accountNumber,
                accountValidationRequestDto.providers
        );

        return new AccountValidationResponseDto(result);
    }

}
