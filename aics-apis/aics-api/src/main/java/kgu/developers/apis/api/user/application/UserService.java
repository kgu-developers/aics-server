package kgu.developers.apis.api.user.application;

import org.springframework.stereotype.Service;

import kgu.developers.core.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
}
