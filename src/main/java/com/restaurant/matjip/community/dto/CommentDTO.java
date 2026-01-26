package com.restaurant.matjip.community.dto;

import com.restaurant.matjip.community.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private Long boardId;
    private Long userId;
    private String content;
    private Long parentId;

    public static CommentDTO fromEntity(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getUser().getId(),
                comment.getContent(),
                comment.getParent() != null ? comment.getParent().getId() : null
        );
    }
}

