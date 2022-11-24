package com.morgan.jp.accounts.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import com.morgan.jp.accounts.providers.DataProviders;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ValidationController {

    private final DataProviders dataProviders;

    @PostMapping("/validate")
    public String validate(@RequestBody AccountValidationRequestDto accountValidationRequestDto) throws Exception {
        ObjectMapper om = new ObjectMapper();
        this.dataProviders.getProviders().forEach(x -> System.out.println(x.name));
        return om.writeValueAsString(accountValidationRequestDto);
    }
}
