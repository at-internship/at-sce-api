package com.agilethought.atsceapi.validator.history;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agilethought.atsceapi.dummy.DummyNewHistoryRequest;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.repository.UserRepository;

public class HistoryValidatorTest {
	private static final String EXCEPTION_ERROR_MESSAGE = "One or more fields are invalid";

	public DummyNewHistoryRequest dummyNewHistoryRequest;

	@Mock
	public UserRepository userRepository;

	@InjectMocks
	public HistoryValidator historyValidator;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		dummyNewHistoryRequest = new DummyNewHistoryRequest();
		dummyNewHistoryRequest.populateHistoryRequest();
	}

	@Test
	public void itShouldThrowErrorMessageHistoryInTypeField() {

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setType(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setType(0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setType(5);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInHistoryUserIdField() {

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setUser_id(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setUser_id("");
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setUser_id("	");
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setUser_id("1");
			when((userRepository).existsById(anyString())).thenReturn(false);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTotalHoursField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setTotalHours(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setTotalHours(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTotalDaysField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setTotalDays(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setTotalDays(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryCostDayField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setCostDay(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setCostDay(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryCostHourField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setCostHour(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setCostHour(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryProjectCostField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setProjectCost(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setProjectCost(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTaxIVAField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setTaxIVA(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setTaxIVA(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTaxISR_rField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setTaxISR_r(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setTaxISR_r(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTaxIVA_rField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setTaxIVA_r(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setTaxIVA_r(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryTotalField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setTotal(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setTotal(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryRevenueField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setRevenue(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setRevenue(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageInHistoryStatusField() {

		when((userRepository).existsById(anyString())).thenReturn(true);

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.setStatus(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setStatus(-1);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.setStatus(2);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});
	}

	@Test
	public void itShouldThrowErrorMessageFixedExpensesInRentField() {

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.fixedExpenses.setRent(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.fixedExpenses.setRent(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesTransportField() {

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.fixedExpenses.setTransport(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.fixedExpenses.setTransport(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesInternetField() {

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.fixedExpenses.setInternet(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.fixedExpenses.setInternet(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesFeedField() {

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.fixedExpenses.setFeed(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.fixedExpenses.setFeed(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesOthersField() {

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.fixedExpenses.setOthers(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.fixedExpenses.setOthers(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}

	@Test
	public void itShouldThrowErrorMessageInFixedExpensesTotalField() {

		Assertions.assertAll(() -> {
			dummyNewHistoryRequest.fixedExpenses.setTotal(null);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.fixedExpenses.setTotal(-1.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		}, () -> {
			dummyNewHistoryRequest.fixedExpenses.setTotal(4.0);
			Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
				historyValidator.validate(dummyNewHistoryRequest);
			});
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
			Assertions.assertEquals(EXCEPTION_ERROR_MESSAGE, errorMessageException.getMessage());
		});

	}
}
