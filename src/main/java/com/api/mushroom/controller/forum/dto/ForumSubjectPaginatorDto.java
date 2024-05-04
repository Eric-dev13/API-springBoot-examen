package com.api.mushroom.controller.forum.dto;

import java.util.List;

public record ForumSubjectPaginatorDto(
        List<ForumSubjectDto> forumSubjects,
        Long forumSubjectLength
) {
}
