package com.smartbear.controller;

import com.smartbear.dto.ErrorResponse;
import com.smartbear.dto.SpokenTimeResponse;
import com.smartbear.service.SpokenTimeService;
import com.smartbear.util.Style;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/speak-time")
@RequiredArgsConstructor
@Validated
public class TimeSpeakerController {

    private final SpokenTimeService spokenTimeService;

    @Operation(summary = "Convert digital time to British spoken time",
            description = "Converts a digital time string (HH:mm) to its British spoken equivalent")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful conversion",
                    content = @Content(schema = @Schema(implementation = SpokenTimeResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid time format",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Endpoint not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<SpokenTimeResponse> speakTime(
            @Parameter(description = "Time in HH:mm format (e.g., 12:30)", example = "12:30", required = true)
            @RequestParam @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$",
                    message = "Time must be in HH:mm format") String time, @RequestParam(required = false) String style) {

        var s = Style.of(style);
        String spokenTime = spokenTimeService.speak(time, s);
        SpokenTimeResponse response = new SpokenTimeResponse(time, s.name(), spokenTime);
        return response.spoken() == null ? ResponseEntity.badRequest().build(): ResponseEntity.ok(response);
    }

    @Operation(summary = "Health check endpoint")
    @ApiResponse(responseCode = "200", description = "Service is healthy")
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Service is healthy");
    }
}
