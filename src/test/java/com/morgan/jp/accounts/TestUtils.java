package com.morgan.jp.accounts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morgan.jp.accounts.models.AccountValidationProvidersResult;
import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import com.morgan.jp.accounts.models.AccountValidationResponseDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static AccountValidationRequestDto newAccountValidationRequestDto() {
        AccountValidationRequestDto requestDto = new AccountValidationRequestDto();
        requestDto.setAccountNumber("123456");
        requestDto.setProviders(new ArrayList<>());
        return requestDto;
    }

    public static List<AccountValidationProvidersResult> validationProvidersResultList() {
        AccountValidationProvidersResult result1 = new AccountValidationProvidersResult();
        result1.setProvider("provider1");
        result1.setValid(true);
        AccountValidationProvidersResult result2 = new AccountValidationProvidersResult();
        result2.setProvider("provider2");
        result2.setValid(true);
        return Arrays.asList(result1, result2);
    }
    public static AccountValidationResponseDto validResponseDto() {
        return new AccountValidationResponseDto(validationProvidersResultList());
    }

    public static String objectAsString(Object obj) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(obj);
    }
}
