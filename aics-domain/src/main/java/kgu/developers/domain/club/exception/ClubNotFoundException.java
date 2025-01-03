package kgu.developers.domain.club.exception;

import static kgu.developers.domain.club.exception.ClubDomainExceptionCode.CLUB_NOT_FOUND;

import kgu.developers.common.exception.CustomException;

public class ClubNotFoundException extends CustomException {
	public ClubNotFoundException() {
		super(CLUB_NOT_FOUND);
	}
}
