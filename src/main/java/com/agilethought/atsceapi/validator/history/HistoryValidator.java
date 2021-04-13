package com.agilethought.atsceapi.validator.history;

import static com.agilethought.atsceapi.exception.ErrorMessage.MISSING_REQUIRED_INPUT;
import static com.agilethought.atsceapi.exception.ErrorMessage.INVALID_INPUT;
import static com.agilethought.atsceapi.exception.ErrorMessage.CORRECT_FORMAT_NUMERIC;
import static com.agilethought.atsceapi.validator.user.ValidationUtils.isValidString;

import com.agilethought.atsceapi.repository.UserRepository;
import com.agilethought.atsceapi.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.dto.history.NewHistoryRequest;
import com.agilethought.atsceapi.exception.BadRequestException;

@Service
public class HistoryValidator implements Validator<NewHistoryRequest> {

	private static final String TYPE = "Type";
	private static final String CORRECT_FORMAT_TYPE = "Number 1 for Custom Web, number 2 for " +
			"e-Commerce, number 3 for Android app, or number 4 for iOS app.";

	private static final String USER_ID = "User id";

	private static final String TOTAL_HOURS = "Total number of hours";
	private static final String TOTAL_DAYS = "Total number of days";
	private static final String CORRECT_FORMAT_INTEGER = "An integer number greater than " +
			"or equal to zero.";

	private static final String COST_DAY = "Cost per day";

	private static final String COST_HOUR = "Cost per hour";

	private static final String PROJECT_COST = "Cost of the project";

	private static final String TAX_IVA = "Tax IVA";

	private static final String TAX_ISR_R = "Tax ISR R";

	private static final String TAX_IVA_R = "Tax IVA R";

	private static final String TOTAL = "Total cost of the project";

	private static final String REVENUE = "Project revenue";

	private static final String STATUS = "Project status";
	private static final String CORRECT_FORMAT_STATUS = "Number 0 for unavailable or" +
			"number 1 for available";

    @Autowired
	private FixedExpensesValidator fixedExpensesValidator;

	@Autowired
	private UserRepository userRepository;
    
	@Override
	public void validate(NewHistoryRequest historyRequest) {

		validateHistoryTypeField(historyRequest.getType());
		validateUserId(historyRequest.getUser_id());
		validateTotalHours(historyRequest.getTotalHours());
		validateTotalDays(historyRequest.getTotalDays());
		validateCostDay(historyRequest.getCostDay());
		validateCostHour(historyRequest.getCostHour());
		validateProjectCost(historyRequest.getProjectCost());
		validateTaxIVA(historyRequest.getTaxIVA());
		validateTaxISR_r(historyRequest.getTaxISR_r());
		validateTaxIVA_r(historyRequest.getTaxIVA_r());
		validateTotal(historyRequest.getTotal());
		validateRevenue(historyRequest.getRevenue());
		validateHistoryStatus(historyRequest.getStatus());
		fixedExpensesValidator.validate(historyRequest.getFixedExpenses());

	}

	private void validateHistoryTypeField(Integer type) {

		if (type == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TYPE)
			);
		}
		if (type < 1 || type > 4) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TYPE, CORRECT_FORMAT_TYPE)
			);
		}

	}

	private void validateUserId(String user_id) {

		if (!isValidString(user_id)) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, USER_ID)
			);
		}
		if (!userRepository.existsById(user_id) || StringUtils.isBlank(user_id)) {
			throw new BadRequestException(
						String.format(MISSING_REQUIRED_INPUT, USER_ID)
			);
		}
	}

	private void validateTotalHours(Integer totalHours) {

		if (totalHours == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TOTAL_HOURS)
			);
		}
		if (totalHours < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TOTAL_HOURS, CORRECT_FORMAT_INTEGER)
			);
		}

	}

	private void validateTotalDays(Integer totalDays) {

		if (totalDays == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TOTAL_DAYS)
			);
		}
		if (totalDays < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TOTAL_DAYS, CORRECT_FORMAT_INTEGER)
			);
		}

	}

	private void validateCostDay(Double costDay) {

		if (costDay == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, COST_DAY)
			);
		}
		if (costDay.isNaN() || costDay < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, COST_DAY, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateCostHour(Double costHour) {

		if (costHour == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, COST_HOUR)
			);
		}
		if (costHour.isNaN() || costHour < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, COST_HOUR, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateProjectCost(Double projectCost) {

		if (projectCost == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, PROJECT_COST)
			);
		}
		if (projectCost.isNaN() || projectCost < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, PROJECT_COST, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateTaxIVA(Double taxIVA) {

		if (taxIVA == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TAX_IVA)
			);
		}
		if (taxIVA.isNaN() || taxIVA < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TAX_IVA, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateTaxISR_r(Double taxISR_r) {

		if (taxISR_r == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TAX_ISR_R)
			);
		}
		if (taxISR_r.isNaN() || taxISR_r < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TAX_ISR_R, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateTaxIVA_r(Double taxIVA_r) {

		if (taxIVA_r == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TAX_IVA_R)
			);
		}
		if (taxIVA_r.isNaN() ||taxIVA_r < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TAX_IVA_R, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateTotal(Double total) {

		if (total == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, TOTAL)
			);
		}
		if (total.isNaN() || total < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, TOTAL, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateRevenue(Double revenue) {

		if (revenue == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, REVENUE)
			);
		}
		if (revenue.isNaN() || revenue < 0) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, REVENUE, CORRECT_FORMAT_NUMERIC)
			);
		}

	}

	private void validateHistoryStatus(Integer status) {

		if (status == null) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, STATUS)
			);
		}
		if (status < 0 || status > 1) {
			throw new BadRequestException(
					String.format(INVALID_INPUT, STATUS, CORRECT_FORMAT_STATUS)
			);
		}

	}

}