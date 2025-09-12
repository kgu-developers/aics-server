package kgu.developers.domain.club.infrastructure;

import jakarta.persistence.*;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.club.domain.Club;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "club")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ClubEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(length = 50)
    private String site;

    @Column(name = "file_id")
    private Long fileId;

    @Builder
    public ClubEntity(Long id,String name, String description, String site, Long fileId) {
        this.name = name;
        this.description = description;
        this.site = site;
        this.fileId = fileId;
    }
    public Club toDomain() {
        return Club.builder()
                .id(id)
                .name(name)
                .description(description)
                .site(site)
                .fileId(fileId)
                .build();
    }
    public static ClubEntity fromDomain(Club club) {
        return ClubEntity.builder()
                .name(club.getName())
                .description(club.getDescription())
                .site(club.getSite())
                .fileId(club.getFileId())
                .build();
    }
}
