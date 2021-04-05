package com.agilethought.atsceapi.validator;

import static com.agilethought.atsceapi.exception.ErrorMessage.BAD_REQUEST_MESSAGE;
import static com.agilethought.atsceapi.exception.ErrorMessage.BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC;

import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.exception.BadRequestException;

@Service
public class FixedExpensesValidator implements Validator<FixedExpenses> {

	@Override
	public void validate(FixedExpenses fixedExpenses) {
		validateRent(fixedExpenses.getRent());
		validateTransport(fixedExpenses.getTransport());
		validateInternet(fixedExpenses.getInternet());
		validateFeed(fixedExpenses.getFeed());
		validateOthers(fixedExpenses.getOthers());
		validateTotal(fixedExpenses.getTotal());
	}

	private void validateRent(Double rent) {
		if (rent == null || rent.isNaN() || rent < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*RENT* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateTransport(Double transport) {
		if (transport == null || transport.isNaN() || transport < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TRANSPORT* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateInternet(Double internet) {
		if (internet == null || internet.isNaN() || internet < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*INTERNET* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateFeed(Double feed) {
		if (feed == null || feed.isNaN() || feed < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*FEED* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateOthers(Double others) {
		if (others == null || others.isNaN() || others < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*OTHERS* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateTotal(Double total) {
		if (total == null || total.isNaN() || total < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TOTAL* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

}
