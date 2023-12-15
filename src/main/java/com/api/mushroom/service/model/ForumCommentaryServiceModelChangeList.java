package com.api.mushroom.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ForumCommentaryServiceModelChangeList {
    private Long id;
    private LocalDateTime createdAt;
    private String commentary;
}
