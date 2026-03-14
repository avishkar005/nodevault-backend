package com.nodevault.nodevaultbackend.dto;

import lombok.Data;

@Data
public class NoteRequest {
    private String title;
    private String content;
    private boolean pinned;
    private boolean favorite;
}