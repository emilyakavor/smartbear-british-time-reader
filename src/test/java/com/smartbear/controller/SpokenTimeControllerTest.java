package com.smartbear.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SpokenTimeControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void apiReturnsExpectedPayload() throws Exception {
        mvc.perform(get("/api/v1/speak-time")
                        .param("time","07:35"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.spoken").value("twenty five to eight"))
                .andExpect(jsonPath("$.style").value("colloquial"))
                .andExpect(jsonPath("$.input").value("07:35"));
    }
}
