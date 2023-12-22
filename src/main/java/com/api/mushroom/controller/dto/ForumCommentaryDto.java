package com.api.mushroom.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ForumCommentaryDto {
    private Long id;
    private LocalDateTime createdAt;
    private String commentary;
    private UserDto user;
    private ForumSubjectDto forumSubject;
}
