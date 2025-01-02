package kgu.developers.domain.professor.application.command;

import org.springframework.stereotype.Service;

import kgu.developers.domain.professor.domain.Professor;
import kgu.developers.domain.professor.domain.ProfessorRepository;
import kgu.developers.domain.professor.domain.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorCommandService {
	private final ProfessorRepository professorRepository;

	public Long createProfessor(String name, Role role, String contact, String email) {
		Professor professor = Professor.create(name, role, contact, email);
		return professorRepository.save(professor).getId();
	}

	public void updateProfessor(Professor professor, String name, String contact, String email, Role role) {
		professor.updateName(name);
		professor.updateContact(contact);
		professor.updateEmail(email);
		professor.updateRole(role);
	}

	public void deleteProfessor(Professor professor) {
		professor.delete();
	}
}
