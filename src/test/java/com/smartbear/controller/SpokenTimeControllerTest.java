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
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
