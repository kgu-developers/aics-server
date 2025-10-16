package kgu.developers.domain.club.infrastructure;

import jakarta.persistence.*;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.club.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "\"club\"")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ClubJpaEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(length = 50)
    private String site;

    private Long fileId;


    public Club toDomain() {
        return Club.builder()
                .id(id)
                .name(name)
                .description(description)
                .site(site)
                .fileId(fileId)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .deletedAt(getDeletedAt())
                .build();
    }
    public static ClubJpaEntity toEntity(Club club) {
        return ClubJpaEntity.builder()
                .id(club.getId())
                .name(club.getName())
                .description(club.getDescription())
                .site(club.getSite())
                .fileId(club.getFileId())
                .build();
    }

}
