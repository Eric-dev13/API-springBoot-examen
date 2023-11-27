package com.api.mushroom.service.forum.model;

import com.api.mushroom.service.user.UserServiceModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ForumCommentaryServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private String commentary;
    private UserServiceModel user;
    private ForumSubjectServiceModel forumSubject;
}
