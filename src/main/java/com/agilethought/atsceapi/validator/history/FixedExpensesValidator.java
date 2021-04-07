package com.agilethought.atsceapi.validator.history;

import static com.agilethought.atsceapi.exception.ErrorMessage.MISSING_REQUIRED_INPUT;
import static com.agilethought.atsceapi.exception.ErrorMessage.INVALID_INPUT;
import static com.agilethought.atsceapi.exception.ErrorMessage.CORRECT_FORMAT_NUMERIC;

import com.agilethought.atsceapi.validator.Validator;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.exception.BadRequestException;

@Service
public class FixedExpensesValidator implements Validator<FixedExpenses> {

	private static final String RENT = "Rent";

	private static final String TRANSPORT = "Transport";

	private static final String INTERNET = "Internet";

	private static final String FEED = "Feed";

	private static final String OTHERS = "Others";

	private static final String TOTAL = "Total";
	private static final String CORRECT_FORMAT_TOTAL = "A number equal to the sum of all other" +
			" fields in the Fixed Expenses section.";

	@Override
	public void validate(FixedExpenses fixedExpenses) {

		validateRent(fixedExpenses.getRent());
		validateTransport(fixedExpenses.getTransport());
		validateInternet(fixedExpenses.getInternet());
		validateFeed(fixedExpenses.getFeed());
		validateOthers(fixedExpenses.getOthers());
		/* After we validate the fields above, we should sum them to know
		if the "total" field has a correct value. */
		Double calculatedTotal = fixedExpenses.getRent() + fixedExpenses.getTransport() +
				fixedExpenses.getInternet() + fixedExpenses.getFeed() + fixedExpenses.getOthers();
		validateTotal(fixedExpenses.getTotal(), calculatedTotal);

	}

	private void validateRent(Double rent) {

		if (rent == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, RENT)
			);
		}
		if (rent.isNaN() || rent < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, RENT, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateTransport(Double transport) {

		if (transport == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TRANSPORT)
			);
		}
		if (transport.isNaN() || transport < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TRANSPORT, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateInternet(Double internet) {

		if (internet == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, INTERNET)
			);
		}
		if (internet.isNaN() || internet < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, INTERNET, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateFeed(Double feed) {

		if (feed == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, FEED)
			);
		}
		if (feed.isNaN() || feed < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, FEED, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateOthers(Double others) {

		if (others == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, OTHERS)
			);
		}
		if (others.isNaN() || others < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, OTHERS, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateTotal(Double receivedTotal, Double calculatedTotal) {

		if (receivedTotal == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TOTAL)
			);
		}
		if (receivedTotal.isNaN() || receivedTotal < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TOTAL, CORRECT_FORMAT_NUMERIC)
			);
		}
		if (!receivedTotal.equals(calculatedTotal)) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TOTAL, CORRECT_FORMAT_TOTAL)
			);
		}

	}

}
