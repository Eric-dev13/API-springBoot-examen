package com.api.mushroom.controller.forum.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ForumSubjectDto(Long id,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt,
                              String title,
                              String description,
                              ForumUserDto user,
                              List<ForumCommentaryDto> comments) { }
