package com.smartbear.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TimeSpeakerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void speakTime_ShouldReturnCorrectSpokenTime_ForValidTimeWithColloquialStyle() throws Exception {
        mockMvc.perform(get("/api/v1/speak-time")
                        .param("time", "12:30")
                        .param("style", "COLLOQUIAL"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.input").value("12:30"))
                .andExpect(jsonPath("$.style").value("COLLOQUIAL"))
                .andExpect(jsonPath("$.spoken").value("half past twelve"));
    }


    @Test
    void speakTime_ShouldReturnBadRequest_ForInvalidTimeFormat() throws Exception {
        mockMvc.perform(get("/api/v1/speak-time")
                        .param("time", "invalid-time")
                        .param("style", "COLLOQUIAL"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value(containsString("Parameter validation failed")))
                .andExpect(jsonPath("$.details.time").value(containsString("Time must be in HH:mm")));
    }

    @Test
    void speakTime_ShouldReturnBadRequest_ForMissingTimeParameter() throws Exception {
        mockMvc.perform(get("/api/v1/speak-time")
                        .param("style", "COLLOQUIAL"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value(containsString("Required parameter is missing: time")));
    }

    @Test
    void speakTime_ShouldHandleVariousTimesCorrectly() throws Exception {
        // Test multiple time scenarios with different styles
        String[][] testCases = {
                {"01:00", "COLLOQUIAL", "one o'clock"},
                {"02:05", "COLLOQUIAL", "five past two"},
                {"03:10", "COLLOQUIAL", "ten past three"},
                {"04:15", "COLLOQUIAL", "quarter past four"},
                {"05:20", "COLLOQUIAL", "twenty past five"},
                {"06:25", "COLLOQUIAL", "twenty five past six"},
                {"07:30", "COLLOQUIAL", "half past seven"},
                {"08:35", "COLLOQUIAL", "twenty five to nine"},
                {"09:40", "COLLOQUIAL", "twenty to ten"},
                {"10:45", "COLLOQUIAL", "quarter to eleven"},
                {"11:50", "COLLOQUIAL", "ten to twelve"},
                {"06:32", "DIGITAL", "six thirty two"},
                {"12:00", "COLLOQUIAL", "noon"},
                {"00:00", "COLLOQUIAL", "midnight"}
        };

        for (String[] testCase : testCases) {
            mockMvc.perform(get("/api/v1/speak-time")
                            .param("time", testCase[0])
                            .param("style", testCase[1]))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.input").value(testCase[0]))
                    .andExpect(jsonPath("$.style").value(testCase[1]))
                    .andExpect(jsonPath("$.spoken").value(testCase[2]));
        }
    }
}
