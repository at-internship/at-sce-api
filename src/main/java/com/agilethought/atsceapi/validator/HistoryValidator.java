package com.agilethought.atsceapi.validator;

import static com.agilethought.atsceapi.exception.ErrorMessage.BAD_REQUEST_MESSAGE;
import static com.agilethought.atsceapi.exception.ErrorMessage.BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC;
import static com.agilethought.atsceapi.exception.ErrorMessage.BAD_REQUEST_MESSAGE_HISTORY_TYPE;
import static com.agilethought.atsceapi.exception.ErrorMessage.BAD_REQUEST_MESSAGE_STATUS;
import static com.agilethought.atsceapi.exception.ErrorMessage.BAD_REQUEST_MESSAGE_USER_ID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.dto.NewHistoryRequest;
import com.agilethought.atsceapi.exception.BadRequestException;

@Service
public class HistoryValidator implements Validator<NewHistoryRequest> {
    @Autowired
	private FixedExpensesValidator fixedExpensesValidator;
    
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
		if (type == null || type < 1 || type > 4) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TYPE* " + BAD_REQUEST_MESSAGE_HISTORY_TYPE);
		}
	}

	private void validateUserId(String user_id) {
		if (StringUtils.isBlank(user_id)) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*USER_ID* " + BAD_REQUEST_MESSAGE_USER_ID);
		}
	}

	private void validateTotalHours(Integer totalHours) {
		if (totalHours == null || totalHours < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TOTALHOURS* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateTotalDays(Integer totalDays) {
		if (totalDays == null || totalDays < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TOTALDAYS* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateCostDay(Double costDay) {
		if (costDay == null || costDay.isNaN() || costDay < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*COSTDAY* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateCostHour(Double costHour) {
		if (costHour == null || costHour.isNaN() || costHour < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*COSTHOUR* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateProjectCost(Double projectCost) {
		if (projectCost == null || projectCost.isNaN() || projectCost < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*PROJECTCOST* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateTaxIVA(Double taxIVA) {
		if (taxIVA == null || taxIVA.isNaN() || taxIVA < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TAXIVA* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateTaxISR_r(Double taxISR_r) {
		if (taxISR_r == null || taxISR_r.isNaN() || taxISR_r < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TAXISR_R* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateTaxIVA_r(Double taxIVA_r) {
		if (taxIVA_r == null || taxIVA_r.isNaN() ||taxIVA_r < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TAXIVA_R* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateTotal(Double total) {
		if (total == null || total.isNaN() || total < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TOTAL* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateRevenue(Double revenue) {
		if (revenue == null || revenue.isNaN() || revenue < 0) {
			throw new BadRequestException(BAD_REQUEST_MESSAGE + "*REVENUE* " + BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC);
		}
	}

	private void validateHistoryStatus(Integer status) {
		if (status != null) {
			if (status != 0 && status != 1) {
				throw new BadRequestException(BAD_REQUEST_MESSAGE_STATUS);
			}
		}

	}

}