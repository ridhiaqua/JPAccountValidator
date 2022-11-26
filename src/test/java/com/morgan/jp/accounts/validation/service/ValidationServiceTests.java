package com.morgan.jp.accounts.validation.service;

import com.morgan.jp.accounts.TestUtils;
import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import com.morgan.jp.accounts.models.AccountValidationResponseDto;
import com.morgan.jp.accounts.providers.IDataProviderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ValidationServiceTests {

    @Mock
    private IDataProviderService dataProviderService;

    @InjectMocks
    private ValidationService validationService;

    @Test
    public void validateRequest_validRequest_success() {
        AccountValidationRequestDto requestDto = TestUtils.newAccountValidationRequestDto();

        when(dataProviderService.validateAccount(anyString(), anyList())).thenReturn(TestUtils.validationProvidersResultList());

        AccountValidationResponseDto response = validationService.validateRequest(requestDto);

        assertThat(response.getProvidersResult().size(), is(2));
        assertThat(response.getProvidersResult().get(0).getProvider(), is("provider1"));
        assertTrue(response.getProvidersResult().get(0).isValid());
    }
}
