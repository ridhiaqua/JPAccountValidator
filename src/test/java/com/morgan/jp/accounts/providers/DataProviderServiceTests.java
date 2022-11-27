package com.morgan.jp.accounts.providers;

import com.google.common.collect.ImmutableMap;
import com.morgan.jp.accounts.models.AccountValidationProvidersResult;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DataProviderServiceTests {

    @Mock
    private DataProviders dataProviders;

    @InjectMocks
    private DataProviderService dataProviderService;

    @Test
    public void validateAccount_validRequest_success() {

        List<AccountValidationProvidersResult> response = dataProviderService.validateAccount(
                "12345", Arrays.asList("p1", "p2", "p3")
        );

        assertThat(response.size(), is(3));
        assertThat(response.get(0).getProvider(), is("p1"));
        assertThat(response.get(1).getProvider(), is("p2"));
        assertThat(response.get(2).getProvider(), is("p3"));
    }

    @Test
    public void validateAccount_emptyProviders_returnDefaultResponse() {
        Map<String, Provider> defaultResponse = ImmutableMap.of(
                "provider1", new Provider(),
                "provider2", new Provider()
        );

        when(dataProviders.getProviders()).thenReturn(defaultResponse);
        List<AccountValidationProvidersResult> response = dataProviderService.validateAccount(
                "12345", new ArrayList<>()
        );

        assertThat(response.size(), is(2));
        assertThat(response.get(0).getProvider(), is("provider1"));
        assertThat(response.get(1).getProvider(), is("provider2"));
    }

    @Test
    public void validateAccount_extraProviders_returnValidTrueIfExistInDefaultProviders() {
        Map<String, Provider> defaultResponse = ImmutableMap.of(
                "provider1", new Provider(),
                "provider2", new Provider()
        );

        when(dataProviders.getProviders()).thenReturn(defaultResponse);

        List<AccountValidationProvidersResult> response = dataProviderService.validateAccount(
                "12345", Arrays.asList("provider1", "provider2", "p3")
        );

        assertThat(response.size(), is(3));
        assertThat(response.get(0).getProvider(), is("provider1"));
        assertTrue(response.get(0).isValid());
        assertThat(response.get(1).getProvider(), is("provider2"));
        assertTrue(response.get(1).isValid());
        assertThat(response.get(2).getProvider(), is("p3"));
        assertFalse(response.get(2).isValid());
    }
}
