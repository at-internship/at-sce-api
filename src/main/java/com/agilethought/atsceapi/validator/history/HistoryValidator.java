package com.agilethought.atsceapi.validator.history;

import static com.agilethought.atsceapi.exception.ErrorMessage.CORRECT_FORMAT_NUMERIC;
import static com.agilethought.atsceapi.exception.ErrorMessage.INVALID_INPUT;
import static com.agilethought.atsceapi.exception.ErrorMessage.MISSING_REQUIRED_INPUT;
import static com.agilethought.atsceapi.exception.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.atsceapi.exception.ErrorMessage.VALIDATION_ERROR;
import static com.agilethought.atsceapi.validator.ValidationUtils.isValidString;

import java.util.ArrayList;
import java.util.List;

import com.agilethought.atsceapi.adaptor.sso.SSOAdaptor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.domain.FixedExpenses;
import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.exception.GlobalExceptionBody.ErrorDetails;
import com.agilethought.atsceapi.validator.Validator;

@Service
public class HistoryValidator implements Validator<NewHistoryRequest> {

	private static final String TYPE = "Type";
	private static final String CORRECT_FORMAT_TYPE = "Number 1 for Custom Web, number 2 for "
			+ "e-Commerce, number 3 for Android app, or number 4 for iOS app.";

	private static final String USER_ID = "User id";

	private static final String TOTAL_HOURS = "Total number of hours";
	private static final String TOTAL_DAYS = "Total number of days";
	private static final String CORRECT_FORMAT_INTEGER = "An integer number greater than " + "or equal to zero.";

	private static final String COST_DAY = "Cost per day";

	private static final String COST_HOUR = "Cost per hour";

	private static final String PROJECT_COST = "Cost of the project";

	private static final String TAX_IVA = "Tax IVA";

	private static final String TAX_ISR_R = "Tax ISR R";

	private static final String TAX_IVA_R = "Tax IVA R";

	private static final String TOTAL = "Total cost of the project";

	private static final String REVENUE = "Project revenue";

	private static final String STATUS = "Project status";
	private static final String CORRECT_FORMAT_STATUS = "Number 0 for unavailable or" + "number 1 for available";

	private static final String RENT = "Rent";

	private static final String TRANSPORT = "Transport";

	private static final String INTERNET = "Internet";

	private static final String FEED = "Feed";

	private static final String OTHERS = "Others";

	private static final String FIXED_EXPENSES_TOTAL = "Total";
	private static final String CORRECT_FORMAT_TOTAL = "A number equal to the sum of all other"
			+ " fields in the Fixed Expenses section.";

	@Autowired
	private SSOAdaptor ssoAdaptor;

	@Override
	public void validate(NewHistoryRequest historyRequest) {
		List<ErrorDetails> errorDetails = new ArrayList<ErrorDetails>();
		validateHistoryTypeField(historyRequest.getType(), errorDetails);
		validateUserId(historyRequest.getUser_id(), errorDetails);
		validateTotalHours(historyRequest.getTotalHours(), errorDetails);
		validateTotalDays(historyRequest.getTotalDays(), errorDetails);
		validateCostDay(historyRequest.getCostDay(), errorDetails);
		validateCostHour(historyRequest.getCostHour(), errorDetails);
		validateProjectCost(historyRequest.getProjectCost(), errorDetails);
		validateTaxIVA(historyRequest.getTaxIVA(), errorDetails);
		validateTaxISR_r(historyRequest.getTaxISR_r(), errorDetails);
		validateTaxIVA_r(historyRequest.getTaxIVA_r(), errorDetails);
		validateTotal(historyRequest.getTotal(), errorDetails);
		validateRevenue(historyRequest.getRevenue(), errorDetails);
		validateHistoryStatus(historyRequest.getStatus(), errorDetails);
		validateFixedExpenses(historyRequest.getFixedExpenses(), errorDetails);
		if (CollectionUtils.isNotEmpty(errorDetails))
			throw new BadRequestException(VALIDATION_ERROR, errorDetails);
	}

	private void validateFixedExpenses(FixedExpenses fixedExpenses, List<ErrorDetails> errorDetails) {
		validateRent(fixedExpenses.getRent(), errorDetails);
		validateTransport(fixedExpenses.getTransport(), errorDetails);
		validateInternet(fixedExpenses.getInternet(), errorDetails);
		validateFeed(fixedExpenses.getFeed(), errorDetails);
		validateOthers(fixedExpenses.getOthers(), errorDetails);
		/*
		 * After we validate the fields above, we should sum them to know if the "total"
		 * field has a correct value.
		 */
		Double calculatedTotal = (fixedExpenses.getRent() == null ? 0 : fixedExpenses.getRent())
				+ (fixedExpenses.getTransport() == null ? 0 : fixedExpenses.getTransport())
				+ (fixedExpenses.getInternet() == null ? 0 : fixedExpenses.getInternet())
				+ (fixedExpenses.getFeed() == null ? 0 : fixedExpenses.getFeed())
				+ (fixedExpenses.getOthers() == null ? 0 : fixedExpenses.getOthers());
		validateTotal(fixedExpenses.getTotal(), calculatedTotal, errorDetails);
	}

	private void validateHistoryTypeField(Integer type, List<ErrorDetails> errorDetails) {

		if (type == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TYPE));
			error.setFieldName(TYPE);
			errorDetails.add(error);
			return;
		}
		if (type < 1 || type > 4) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TYPE, CORRECT_FORMAT_TYPE));
			error.setFieldName(TYPE);
			errorDetails.add(error);
			return;
		}

	}

	private void validateUserId(String user_id, List<ErrorDetails> errorDetails) {

		if (!isValidString(user_id)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, USER_ID));
			error.setFieldName(USER_ID);
			errorDetails.add(error);
			return;
		}
		boolean userIdExists = false;
		try {
			userIdExists = ssoAdaptor.existsById(user_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (!userIdExists) {
				ErrorDetails error = new ErrorDetails();
				error.setErrorMessage(String.format(NOT_FOUND_RESOURCE, USER_ID, user_id));
				error.setFieldName(USER_ID);
				errorDetails.add(error);
			}
		}
	}

	private void validateTotalHours(Integer totalHours, List<ErrorDetails> errorDetails) {

		if (totalHours == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TOTAL_HOURS));
			error.setFieldName(TOTAL_HOURS);
			errorDetails.add(error);
			return;
		}
		if (totalHours < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TOTAL_HOURS, CORRECT_FORMAT_INTEGER));
			error.setFieldName(TOTAL_HOURS);
			errorDetails.add(error);
			return;
		}

	}

	private void validateTotalDays(Integer totalDays, List<ErrorDetails> errorDetails) {

		if (totalDays == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TOTAL_DAYS));
			error.setFieldName(TOTAL_DAYS);
			errorDetails.add(error);
			return;
		}
		if (totalDays < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TOTAL_DAYS, CORRECT_FORMAT_INTEGER));
			error.setFieldName(TOTAL_DAYS);
			errorDetails.add(error);
			return;
		}

	}

	private void validateCostDay(Double costDay, List<ErrorDetails> errorDetails) {

		if (costDay == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, COST_DAY));
			error.setFieldName(COST_DAY);
			errorDetails.add(error);
			return;
		}
		if (costDay.isNaN() || costDay < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, COST_DAY, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(COST_DAY);
			errorDetails.add(error);
			return;
		}

	}

	private void validateCostHour(Double costHour, List<ErrorDetails> errorDetails) {

		if (costHour == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, COST_HOUR));
			error.setFieldName(COST_HOUR);
			errorDetails.add(error);
			return;
		}
		if (costHour.isNaN() || costHour < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, COST_HOUR, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(COST_HOUR);
			errorDetails.add(error);
			return;
		}

	}

	private void validateProjectCost(Double projectCost, List<ErrorDetails> errorDetails) {

		if (projectCost == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, PROJECT_COST));
			error.setFieldName(PROJECT_COST);
			errorDetails.add(error);
			return;
		}
		if (projectCost.isNaN() || projectCost < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, PROJECT_COST, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(PROJECT_COST);
			errorDetails.add(error);
			return;
		}

	}

	private void validateTaxIVA(Double taxIVA, List<ErrorDetails> errorDetails) {

		if (taxIVA == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TAX_IVA));
			error.setFieldName(TAX_IVA);
			errorDetails.add(error);
			return;
		}
		if (taxIVA.isNaN() || taxIVA < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TAX_IVA, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(TAX_IVA);
			errorDetails.add(error);
			return;
		}

	}

	private void validateTaxISR_r(Double taxISR_r, List<ErrorDetails> errorDetails) {

		if (taxISR_r == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TAX_ISR_R));
			error.setFieldName(TAX_ISR_R);
			errorDetails.add(error);
			return;
		}
		if (taxISR_r.isNaN() || taxISR_r < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TAX_ISR_R, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(TAX_ISR_R);
			errorDetails.add(error);
			return;
		}

	}

	private void validateTaxIVA_r(Double taxIVA_r, List<ErrorDetails> errorDetails) {

		if (taxIVA_r == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TAX_IVA_R));
			error.setFieldName(TAX_IVA_R);
			errorDetails.add(error);
			return;
		}
		if (taxIVA_r.isNaN() || taxIVA_r < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TAX_IVA_R, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(TAX_IVA_R);
			errorDetails.add(error);
			return;
		}

	}

	private void validateTotal(Double total, List<ErrorDetails> errorDetails) {

		if (total == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TOTAL));
			error.setFieldName(TOTAL);
			errorDetails.add(error);
			return;
		}
		if (total.isNaN() || total < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TOTAL, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(TOTAL);
			errorDetails.add(error);
			return;
		}

	}

	private void validateRevenue(Double revenue, List<ErrorDetails> errorDetails) {

		if (revenue == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, REVENUE));
			error.setFieldName(REVENUE);
			errorDetails.add(error);
			return;
		}
		if (revenue.isNaN() || revenue < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, REVENUE, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(REVENUE);
			errorDetails.add(error);
			return;
		}

	}

	private void validateHistoryStatus(Integer status, List<ErrorDetails> errorDetails) {

		if (status == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, STATUS));
			error.setFieldName(STATUS);
			errorDetails.add(error);
			return;
		}
		if (status < 0 || status > 1) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, STATUS, CORRECT_FORMAT_STATUS));
			error.setFieldName(STATUS);
			errorDetails.add(error);
			return;
		}

	}

	private void validateRent(Double rent, List<ErrorDetails> errorDetails) {

		if (rent == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, RENT));
			error.setFieldName(RENT);
			errorDetails.add(error);
			return;
		}
		if (rent.isNaN() || rent < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, RENT, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(RENT);
			errorDetails.add(error);
			return;
		}

	}

	private void validateTransport(Double transport, List<ErrorDetails> errorDetails) {

		if (transport == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TRANSPORT));
			error.setFieldName(TRANSPORT);
			errorDetails.add(error);
			return;
		}
		if (transport.isNaN() || transport < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TRANSPORT, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(TRANSPORT);
			errorDetails.add(error);
			return;
		}

	}

	private void validateInternet(Double internet, List<ErrorDetails> errorDetails) {

		if (internet == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, INTERNET));
			error.setFieldName(INTERNET);
			errorDetails.add(error);
			return;
		}
		if (internet.isNaN() || internet < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, INTERNET, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(INTERNET);
			errorDetails.add(error);
			return;
		}

	}

	private void validateFeed(Double feed, List<ErrorDetails> errorDetails) {

		if (feed == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, FEED));
			error.setFieldName(FEED);
			errorDetails.add(error);
			return;
		}
		if (feed.isNaN() || feed < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, FEED, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(FEED);
			errorDetails.add(error);
			return;
		}

	}

	private void validateOthers(Double others, List<ErrorDetails> errorDetails) {

		if (others == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, OTHERS));
			error.setFieldName(OTHERS);
			errorDetails.add(error);
			return;
		}
		if (others.isNaN() || others < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, OTHERS, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(OTHERS);
			errorDetails.add(error);
			return;
		}

	}

	private void validateTotal(Double receivedTotal, Double calculatedTotal, List<ErrorDetails> errorDetails) {

		if (receivedTotal == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, FIXED_EXPENSES_TOTAL));
			error.setFieldName(FIXED_EXPENSES_TOTAL);
			errorDetails.add(error);
			return;
		}
		if (receivedTotal.isNaN() || receivedTotal < 0) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, FIXED_EXPENSES_TOTAL, CORRECT_FORMAT_NUMERIC));
			error.setFieldName(FIXED_EXPENSES_TOTAL);
			errorDetails.add(error);
			return;
		}
		if (!Precision.equals(receivedTotal, calculatedTotal)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, FIXED_EXPENSES_TOTAL, CORRECT_FORMAT_TOTAL));
			error.setFieldName(FIXED_EXPENSES_TOTAL);
			errorDetails.add(error);
			return;
		}
	}
}