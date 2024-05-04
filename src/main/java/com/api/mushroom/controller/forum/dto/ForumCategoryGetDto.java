package com.api.mushroom.controller.forum.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ForumCategoryGetDto {
        private Long id;
        private LocalDateTime createdAt;
        private String name;
        private List<ForumSubjectDto> forumSubjects;
}
