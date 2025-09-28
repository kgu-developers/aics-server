package kgu.developers.domain.comment.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class CommentJpaEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String authorId;

    public static CommentJpaEntity toEntity(final Comment comment) {

        if ( comment == null ) {
            return null;
        }

        CommentJpaEntity entity = CommentJpaEntity.builder()
            .id(comment.getId())
            .content(comment.getContent())
            .postId(comment.getPostId())
            .authorId(comment.getAuthorId())
            .build();

        entity.setDeletedAt(comment.getDeletedAt());

        return entity;
    }

    public static Comment toDomain(CommentJpaEntity entity) {

        if ( entity == null ) {
            return null;
        }

        return Comment.builder()
            .id(entity.getId())
            .content(entity.getContent())
            .postId(entity.getPostId())
            .authorId(entity.getAuthorId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }

}