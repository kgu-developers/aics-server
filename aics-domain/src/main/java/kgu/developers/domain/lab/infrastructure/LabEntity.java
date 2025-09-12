package kgu.developers.domain.lab.infrastructure;

import jakarta.persistence.*;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.lab.domain.Lab;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;


@Entity
@Table(name = "lab")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class LabEntity extends BaseTimeEntity {
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

    @Column(name = "file_id")
    private Long fileId;

    @Builder
    public LabEntity(String name, String loc, String site, String advisor, Long fileId) {
        this.name = name;
        this.loc = loc;
        this.site = site;
        this.advisor = advisor;
        this.fileId = fileId;
    }
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
    public static LabEntity fromDomain(Lab lab) {
        return LabEntity.builder()
                .name(lab.getName())
                .loc(lab.getLoc())
                .site(lab.getSite())
                .advisor(lab.getAdvisor())
                .fileId(lab.getFileId())
                .build();
    }
}
