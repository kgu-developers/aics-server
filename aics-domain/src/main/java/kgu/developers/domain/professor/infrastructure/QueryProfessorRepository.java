package kgu.developers.domain.professor.infrastructure;

import static kgu.developers.domain.professor.domain.QProfessor.professor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.Role;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryProfessorRepository {
	private final JPAQueryFactory queryFactory;

	public List<Professor> findAllOrderByRoleAndName() {
		return queryFactory.selectFrom(professor)
			.orderBy(
				new CaseBuilder()
					.when(professor.role.eq(Role.PROFESSOR)).then(1)
					.when(professor.role.eq(Role.ASSISTANT)).then(2)
					.otherwise(3)
					.asc(),
				professor.name.asc()
			)
			.fetch();
	}
}
