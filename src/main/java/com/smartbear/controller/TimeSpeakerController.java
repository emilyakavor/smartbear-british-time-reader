package com.smartbear.controller;

import com.smartbear.dto.SpokenTimeResponse;
import com.smartbear.service.SpokenTimeService;
import com.smartbear.util.Style;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/speak-time", produces = MediaType.APPLICATION_JSON_VALUE)
public class TimeSpeakerController {

    private final SpokenTimeService service;
    public TimeSpeakerController(SpokenTimeService service) {
        this.service = service;
    }

    @GetMapping
    public SpokenTimeResponse speak(
            @RequestParam
            @Pattern(regexp = "^[0-2]\\d:[0-5]\\d$", message = "time must be HH:mm") String time,
            @RequestParam(required = false) String style) {

        var s = Style.of(style);
        var spoken = service.speak(time, s);
        return new SpokenTimeResponse(time, s.name(), spoken);
    }
}
