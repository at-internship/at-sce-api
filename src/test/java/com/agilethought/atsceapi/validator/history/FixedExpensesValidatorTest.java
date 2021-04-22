package com.agilethought.atsceapi.validator.history;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.exception.BadRequestException;

public class FixedExpensesValidatorTest {

	FixedExpenses fixedExpenses;

	@InjectMocks
	FixedExpensesValidator fixedExpensesValidator;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		populateFixedExpenses();
	}

	public void populateFixedExpenses() {
		fixedExpenses = new FixedExpenses();
		fixedExpenses.setRent(1.0);
		fixedExpenses.setTransport(1.0);
		fixedExpenses.setInternet(1.0);
		fixedExpenses.setFeed(1.0);
		fixedExpenses.setOthers(1.0);
		fixedExpenses.setTotal(4.0);
	}

	@Test
	public void itShouldThrowErrorMessageInRentField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setRent(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals("Required field Rent is missing.", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setRent(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals(
					"Invalid input on field Rent. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInTransportField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setTransport(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals("Required field Transport is missing.", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setTransport(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals(
					"Invalid input on field Transport. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInInternetField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setInternet(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals("Required field Internet is missing.", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setInternet(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals(
					"Invalid input on field Internet. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFeedField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setFeed(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals("Required field Feed is missing.", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setFeed(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals(
					"Invalid input on field Feed. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInOthersField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setOthers(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals("Required field Others is missing.", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setOthers(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals(
					"Invalid input on field Others. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInTotalField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setTotal(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals("Required field Total is missing.", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setTotal(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals(
					"Invalid input on field Total. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setTotal(4.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				fixedExpensesValidator.validate(fixedExpenses);
			});
			Assertions.assertEquals(
					"Invalid input on field Total. Correct format is: A number equal to the sum of all other fields in the Fixed Expenses section.",
					errorMessageException.getMessage());
		});

	}
}
