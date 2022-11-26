package com.morgan.jp.accounts.validation;

import com.morgan.jp.accounts.TestUtils;
import com.morgan.jp.accounts.models.AccountValidationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ValidationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validate_validRequest_success() throws Exception {
        AccountValidationRequestDto request = TestUtils.newAccountValidationRequestDto();

        this.mockMvc.perform(post("/validate")
                        .content(TestUtils.objectAsString(request))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(TestUtils.objectAsString(TestUtils.validResponseDto()))));
    }

    @Test
    public void validate_invalidRequest_throwsBadRequestException() throws Exception {
        AccountValidationRequestDto requestWithoutAccountNo = new AccountValidationRequestDto();

        this.mockMvc.perform(post("/validate")
                        .content(TestUtils.objectAsString(requestWithoutAccountNo))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isBadRequest());
    }
}
