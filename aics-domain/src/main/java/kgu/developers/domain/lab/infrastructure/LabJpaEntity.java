package kgu.developers.domain.lab.infrastructure;

import jakarta.persistence.*;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.lab.domain.Lab;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;


@Entity
@Table(name = "\"lab\"")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class LabJpaEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 16)
    private String name;

    @Column(nullable = false, length = 10)
    private String loc;

    @Column(nullable = false, length = 50)
    private String site;

    @Column(nullable = false, length = 16)
    private String advisor;

    private Long fileId;

    public Lab toDomain(){
        return Lab.builder()
                .id(id)
                .name(name)
                .loc(loc)
                .site(site)
                .advisor(advisor)
                .fileId(fileId)
                .build();
    }
    public static LabJpaEntity toEntity(Lab lab) {
        return LabJpaEntity.builder()
                .id(lab.getId())
                .name(lab.getName())
                .loc(lab.getLoc())
                .site(lab.getSite())
                .advisor(lab.getAdvisor())
                .fileId(lab.getFileId())
                .build();
    }
}
