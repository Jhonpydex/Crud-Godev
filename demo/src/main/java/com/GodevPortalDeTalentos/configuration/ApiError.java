package com.GodevPortalDeTalentos.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private List<String> message;
    private String path;
}
