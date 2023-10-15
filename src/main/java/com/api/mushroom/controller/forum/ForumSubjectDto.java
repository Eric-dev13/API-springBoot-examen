package com.api.mushroom.controller.forum;

import com.api.mushroom.entity.ForumCommentaryEntity;
import com.api.mushroom.entity.UserEntity;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.List;

public record ForumSubjectDto(Long id,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              String title,
                              String description,
                              UserEntity userEntity,
                              List<ForumCommentaryEntity> forumCommentaryEntities) { }
