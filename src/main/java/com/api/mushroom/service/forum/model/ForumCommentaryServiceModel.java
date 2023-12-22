package com.api.mushroom.service.forum.model;


import com.api.mushroom.service.user.model.UserServiceModelForForum;
import com.api.mushroom.service.model.UserServiceModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ForumCommentaryServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private String commentary;
    private UserServiceModelForForum user;
//    private UserServiceModel user;
    private ForumSubjectServiceModel forumSubject;
}
