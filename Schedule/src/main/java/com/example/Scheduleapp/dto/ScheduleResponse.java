package com.example.scheduleapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponse {
    private Long id;
    private String title;
    private String content;
    private String createdAt;

    public ScheduleResponse(Long id, String title, String content, String createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}