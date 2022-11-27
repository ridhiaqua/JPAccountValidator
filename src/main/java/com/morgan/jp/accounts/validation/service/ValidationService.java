package com.morgan.jp.accounts.validation.service;

import com.morgan.jp.accounts.models.AccountValidationProvidersResult;
import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import com.morgan.jp.accounts.models.AccountValidationResponseDto;
import com.morgan.jp.accounts.providers.IDataProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ValidationService implements IValidationService {

    private final IDataProviderService dataProviderService;

    @Autowired
    public ValidationService(IDataProviderService dataProviderService) {
        this.dataProviderService = dataProviderService;
    }

    @Override
    public AccountValidationResponseDto validateRequest(AccountValidationRequestDto accountValidationRequestDto) {
        log.info("Validation request received for account no: " + accountValidationRequestDto.getAccountNumber());
        List<AccountValidationProvidersResult> result = dataProviderService.validateAccount(
                accountValidationRequestDto.getAccountNumber(),
                accountValidationRequestDto.getProviders()
        );

        return new AccountValidationResponseDto(result);
    }
}
