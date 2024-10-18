package kgu.developers.core.domain.post;


import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import kgu.developers.core.common.domain.BaseTimeEntity;
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

    /*
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