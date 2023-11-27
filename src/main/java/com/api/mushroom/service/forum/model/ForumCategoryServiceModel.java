package com.api.mushroom.service.forum.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ForumCategoryServiceModel {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private List<ForumSubjectServiceModelForCategory> forumSubjects = new ArrayList<>();
}
