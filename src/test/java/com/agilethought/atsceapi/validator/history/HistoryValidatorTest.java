package com.agilethought.atsceapi.validator.history;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.repository.UserRepository;

public class HistoryValidatorTest {

	public NewHistoryRequest newHistoryRequest;

	public FixedExpenses fixedExpenses;

	@Mock
	public FixedExpensesValidator fixedExpensesValidator;

	@Mock
	public UserRepository userRepository;

	@InjectMocks
	public HistoryValidator historyValidator;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		populateHistoryRequest();
	}

	public void populateHistoryRequest() {
		fixedExpenses = new FixedExpenses();
		fixedExpenses.setRent(1.0);
		fixedExpenses.setTransport(1.0);
		fixedExpenses.setInternet(1.0);
		fixedExpenses.setFeed(1.0);
		fixedExpenses.setOthers(1.0);
		fixedExpenses.setTotal(5.0);

		newHistoryRequest = new NewHistoryRequest();
		newHistoryRequest.setType(1);
		newHistoryRequest.setUser_id("1");
		newHistoryRequest.setFixedExpenses(fixedExpenses);
		newHistoryRequest.setTotalHours(1);
		newHistoryRequest.setTotalDays(1);
		newHistoryRequest.setCostDay(1.0);
		newHistoryRequest.setCostHour(1.0);
		newHistoryRequest.setProjectCost(1.0);
		newHistoryRequest.setTaxIVA(1.0);
		newHistoryRequest.setTaxISR_r(1.0);
		newHistoryRequest.setTaxIVA_r(1.0);
		newHistoryRequest.setTotal(1.0);
		newHistoryRequest.setRevenue(1.0);
		newHistoryRequest.setStatus(1);
	}

	@Test
	public void itShouldThrowErrorMessageInTypeField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);

		Assertions.assertAll(() -> {
			newHistoryRequest.setType(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Type is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setType(0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Type. Correct format is: Number 1 for Custom Web, number 2 for e-Commerce, number 3 for Android app, or number 4 for iOS app.",
					errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setType(5);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Type. Correct format is: Number 1 for Custom Web, number 2 for e-Commerce, number 3 for Android app, or number 4 for iOS app.",
					errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInUserIdField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);

		Assertions.assertAll(() -> {
			newHistoryRequest.setUser_id(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field User id is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setUser_id("");
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field User id is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setUser_id("	");
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field User id is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setUser_id("1");
			when((userRepository).existsById(anyString())).thenReturn(false);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field User id is missing.", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInTotalHoursField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTotalHours(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Total number of hours is missing.",
					errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTotalHours(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Total number of hours. Correct format is: An integer number greater than or equal to zero.",
					errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInTotalDaysField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTotalDays(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Total number of days is missing.",
					errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTotalDays(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Total number of days. Correct format is: An integer number greater than or equal to zero.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInCostDayField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setCostDay(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Cost per day is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setCostDay(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Cost per day. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInCostHourField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setCostHour(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Cost per hour is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setCostHour(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Cost per hour. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInProjectCostField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setProjectCost(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Cost of the project is missing.",
					errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setProjectCost(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Cost of the project. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInTaxIVAField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTaxIVA(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Tax IVA is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTaxIVA(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Tax IVA. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInTaxISR_rField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTaxISR_r(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Tax ISR R is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTaxISR_r(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Tax ISR R. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInTaxIVA_rField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTaxIVA_r(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Tax IVA R is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTaxIVA_r(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Tax IVA R. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInTotalField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTotal(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Total cost of the project is missing.",
					errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTotal(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Total cost of the project. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInRevenueField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setRevenue(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Project revenue is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setRevenue(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Project revenue. Correct format is: A number with or without a decimal point.",
					errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInStatusField() {

		doNothing().when(fixedExpensesValidator).validate(fixedExpenses);
		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setStatus(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("Required field Project status is missing.", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setStatus(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Project status. Correct format is: Number 0 for unavailable ornumber 1 for available",
					errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setStatus(2);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals(
					"Invalid input on field Project status. Correct format is: Number 0 for unavailable ornumber 1 for available",
					errorMessageException.getMessage());
		});
	}
}
