package com.agilethought.atsceapi.validator.history;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.exception.GlobalExceptionBody.ErrorDetails;
import com.agilethought.atsceapi.repository.UserRepository;

public class HistoryValidatorTest {

	public NewHistoryRequest newHistoryRequest;

	public FixedExpenses fixedExpenses;

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
	public void itShouldThrowErrorMessageHistoryInTypeField() {

		Assertions.assertAll(() -> {
			newHistoryRequest.setType(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setType(0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setType(5);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInHistoryUserIdField() {

		Assertions.assertAll(() -> {
			newHistoryRequest.setUser_id(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setUser_id("");
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setUser_id("	");
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setUser_id("1");
			when((userRepository).existsById(anyString())).thenReturn(false);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTotalHoursField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTotalHours(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTotalHours(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTotalDaysField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTotalDays(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTotalDays(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryCostDayField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setCostDay(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setCostDay(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryCostHourField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setCostHour(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setCostHour(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryProjectCostField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setProjectCost(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setProjectCost(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTaxIVAField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTaxIVA(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTaxIVA(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTaxISR_rField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTaxISR_r(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTaxISR_r(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTaxIVA_rField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTaxIVA_r(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTaxIVA_r(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTotalField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setTotal(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setTotal(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryRevenueField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setRevenue(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setRevenue(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryStatusField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			newHistoryRequest.setStatus(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setStatus(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			newHistoryRequest.setStatus(2);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageFixedExpensesInRentField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setRent(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setRent(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesTransportField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setTransport(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setTransport(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesInternetField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setInternet(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setInternet(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesFeedField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setFeed(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setFeed(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesOthersField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setOthers(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setOthers(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesTotalField() {

		Assertions.assertAll(() -> {
			fixedExpenses.setTotal(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setTotal(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		}, () -> {
			fixedExpenses.setTotal(4.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(newHistoryRequest);
			});
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
			Assertions.assertEquals("One or more fields are invalid", errorMessageException.getMessage());
		});

	}
}
