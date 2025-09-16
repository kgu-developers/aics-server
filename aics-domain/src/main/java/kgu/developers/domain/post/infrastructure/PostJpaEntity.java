package kgu.developers.domain.post.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.post.domain.Category;
import kgu.developers.domain.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class PostJpaEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private int views;

    @Enumerated(STRING)
    private Category category;

    @Column(nullable = false, length = 10)
    private String authorId;

    @Column(nullable = false)
    private boolean isPinned;

    private Long fileId;

    public static PostJpaEntity toEntity(final Post post) {

        if ( post == null ) {
            return null;
        }

        PostJpaEntity entity = PostJpaEntity.builder()
            .id(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .views(post.getViews())
            .category(post.getCategory())
            .isPinned(post.isPinned())
            .authorId(post.getAuthorId())
            .fileId(post.getFileId())
            .build();

        entity.setDeletedAt(post.getDeletedAt());

        return entity;
    }

    public static Post toDomain(PostJpaEntity entity) {

        if ( entity == null ) {
            return null;
        }

        return Post.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .views(entity.getViews())
            .category(entity.getCategory())
            .isPinned(entity.isPinned())
            .authorId(entity.getAuthorId())
            .fileId(entity.getFileId())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .deletedAt(entity.getDeletedAt())
            .build();
    }
}