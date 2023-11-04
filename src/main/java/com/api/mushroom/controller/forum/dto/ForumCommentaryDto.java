package com.api.mushroom.controller.forum.dto;

import java.time.LocalDateTime;

public record ForumCommentaryDto(LocalDateTime createdAt,
                                 String commentary,
                                 ForumUserDto userCommentaryEditor
                                 ) { }
