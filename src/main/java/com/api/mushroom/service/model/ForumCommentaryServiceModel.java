package com.api.mushroom.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ForumCommentaryServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private String commentary;
    private UserServiceModel user;
    private ForumSubjectServiceModel forumSubject;
}
