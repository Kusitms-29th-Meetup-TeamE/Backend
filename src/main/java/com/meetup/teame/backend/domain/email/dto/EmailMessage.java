package com.meetup.teame.backend.domain.email.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailMessage {

    private String receiver;
    private String title;
    private String content;

    public void setEmailContents(String receiver, String title, String content) {
        this.receiver = content;
        this.title = title;
        this.content = content;
    }

}
