package kgu.developers.domain.about.infrastructure;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.*;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.about.domain.About;
import kgu.developers.domain.about.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="\"about\"")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class AboutJpaEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(STRING)
    private Category category;

    @Column(nullable = false, columnDefinition = "text")
    private String content;


    public About toDomain(){
        return About.builder()
                .id(this.id)
                .category(this.category)
                .content(this.content)
                .build();
    }
    public static AboutJpaEntity toEntity(About about){
        return AboutJpaEntity.builder()
                .id(about.getId())
                .category(about.getCategory())
                .content(about.getContent())
                .build();
    }

}
