package com.morgan.jp.accounts.validation.service;

import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import com.morgan.jp.accounts.models.AccountValidationResponseDto;

public interface IValidationService {

     AccountValidationResponseDto validateRequest(AccountValidationRequestDto request);
}
