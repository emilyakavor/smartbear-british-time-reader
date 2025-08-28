package com.smartbear.controller;

import com.smartbear.dto.SpokenTimeResponse;
import com.smartbear.service.SpokenTimeService;
import com.smartbear.util.Style;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SpokenTimeControllerTest {

    @Autowired
    MockMvc mvc;

    @Mock
    private SpokenTimeService spokenTimeService;

    @InjectMocks
    private TimeSpeakerController timeSpeakerController;

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

    /**
     * This test the bad request at the filter level
     */
    @Test
    void apiReturnsExpectedPayload_bad_request() throws Exception {
        mvc.perform(get("/api/v1/speak-time")
                        .param("time","07:99"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Parameter validation failed"));
    }

    @Test
    void speakTime_ShouldReturnSuccessResponse_ForValidTime() {
        // Arrange
        String inputTime = "12:30";
        String expectedSpokenTime = "half past twelve";
        var s = Style.of(null);
        when(spokenTimeService.speak(inputTime, s)).thenReturn(expectedSpokenTime);

        // Act
        ResponseEntity<SpokenTimeResponse> response = timeSpeakerController.speakTime(inputTime, null);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        SpokenTimeResponse responseBody = response.getBody();
        assertEquals(inputTime, responseBody.input());
        assertEquals(expectedSpokenTime, responseBody.spoken());

        verify(spokenTimeService, times(1)).speak(inputTime, s);
    }

    @Test
    void health_ShouldReturnOkStatus() {
        // Act
        ResponseEntity<String> response = timeSpeakerController.health();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Service is healthy", response.getBody());
    }

}
