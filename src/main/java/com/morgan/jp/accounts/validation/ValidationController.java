package com.morgan.jp.accounts.validation;

import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import com.morgan.jp.accounts.models.AccountValidationResponseDto;
import com.morgan.jp.accounts.validation.service.IValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ValidationController {

    IValidationService validationService;

    @Autowired
    public ValidationController(IValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping("/validate")
    public AccountValidationResponseDto validate(@Valid @RequestBody AccountValidationRequestDto accountValidationRequestDto) throws Exception {
        return validationService.requestDtoProvidersValidator(accountValidationRequestDto);
    }
}
