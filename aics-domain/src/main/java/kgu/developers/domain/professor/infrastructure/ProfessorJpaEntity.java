package kgu.developers.domain.professor.infrastructure;

import jakarta.persistence.*;
import kgu.developers.common.domain.BaseTimeEntity;
import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "\"professor\"")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ProfessorJpaEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    @Enumerated(STRING)
    private Role role;

    @Column(nullable = false, unique = true, length = 15)
    private String contact;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String img;

    @Column(nullable = false, length = 20)
    private String officeLoc;


    public static ProfessorJpaEntity toEntity(Professor professor) {
        ProfessorJpaEntity entity = ProfessorJpaEntity.builder()
                .id(professor.getId())
                .name(professor.getName())
                .role(professor.getRole())
                .contact(professor.getContact())
                .email(professor.getEmail())
                .img(professor.getImg())
                .officeLoc(professor.getOfficeLoc())
                .build();
        entity.deletedAt = professor.getDeletedAt();
        return entity;
    }
    public Professor toDomain(){
        return Professor.builder()
                .id(id)
                .name(name)
                .role(role)
                .contact(contact)
                .email(email)
                .img(img)
                .officeLoc(officeLoc)
                .deletedAt(getDeletedAt())
                .build();

    }

}
