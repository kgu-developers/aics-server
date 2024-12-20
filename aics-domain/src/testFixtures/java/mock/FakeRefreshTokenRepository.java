package mock;

import kgu.developers.domain.refreshtoken.domain.RefreshToken;
import kgu.developers.domain.refreshtoken.domain.RefreshTokenRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeRefreshTokenRepository implements RefreshTokenRepository {
	Map<String, RefreshToken> fakeRedis = new HashMap<>();

	@Override
	public <S extends RefreshToken> S save(S entity) {
		fakeRedis.put(entity.getRefreshToken(), entity);
		return entity;
	}

	@Override
	public Optional<RefreshToken> findById(String id) {
		return Optional.ofNullable(fakeRedis.get(id));
	}

	@Override
	public Optional<RefreshToken> findByRefreshToken(String refreshToken) {
		return Optional.ofNullable(fakeRedis.get(refreshToken));
	}

	/*
		fake 기능 미구현
	 */

	@Override
	public <S extends RefreshToken> Iterable<S> saveAll(Iterable<S> entities) {
		return null;
	}

	@Override
	public boolean existsById(String s) {
		return false;
	}

	@Override
	public Iterable<RefreshToken> findAll() {
		return null;
	}

	@Override
	public Iterable<RefreshToken> findAllById(Iterable<String> strings) {
		return null;
	}


	@Override
	public long count() {
		return 0;
	}

	@Override
	public void deleteById(String s) {

	}


	@Override
	public void delete(RefreshToken entity) {

	}

	@Override
	public void deleteAllById(Iterable<? extends String> strings) {

	}


	@Override
	public void deleteAll(Iterable<? extends RefreshToken> entities) {

	}

	@Override
	public void deleteAll() {

	}
}
