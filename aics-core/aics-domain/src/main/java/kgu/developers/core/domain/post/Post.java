package kgu.developers.core.domain.post;


import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import kgu.developers.core.common.domain.BaseTimeEntity;
import kgu.developers.core.domain.comment.Comment;
import kgu.developers.core.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 3000)
    private String content;

    @Column(nullable = false)
    private int views;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @Builder.Default
    @OneToMany(mappedBy = "post", fetch = LAZY, cascade = ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /*
    TODO: 파일 엔티티 생성 후 연결 & create 메서드에 추가
    @ManyToOne
    @JoinColumn(name = "file_id")
    private File fileID;
    */

    public static Post create(String title, String content, Category category, User author) {
        return Post.builder()
            .title(title)
            .content(content)
            .views(0)
            .category(category)
            .author(author)
            .build();
    }
}
