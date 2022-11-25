package com.morgan.jp.accounts.validation.service;

import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import com.morgan.jp.accounts.models.AccountValidationResponseDto;
import com.morgan.jp.accounts.providers.DataProviders;
import com.morgan.jp.accounts.providers.IDataProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService implements IValidationService {

    private final DataProviders dataProviders;
    IDataProviderService dataProviderService;

    @Autowired
    public ValidationService(DataProviders dataProviders, IDataProviderService dataProviderService) {
        this.dataProviderService = dataProviderService;
        this.dataProviders = dataProviders;
    }

    @Override
    public AccountValidationResponseDto requestDtoProvidersValidator(AccountValidationRequestDto accountValidationRequestDto) {

        return new AccountValidationResponseDto(dataProviderService.validateAccount(
                accountValidationRequestDto.accountNumber,
                accountValidationRequestDto.providers)
        );
    }

}
