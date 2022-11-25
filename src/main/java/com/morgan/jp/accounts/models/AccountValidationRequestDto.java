package com.morgan.jp.accounts.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AccountValidationRequestDto {

    @NotBlank
    public String accountNumber;

    public List<String> providers;
}
