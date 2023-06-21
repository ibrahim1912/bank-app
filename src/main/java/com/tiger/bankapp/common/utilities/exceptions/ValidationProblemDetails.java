package com.tiger.bankapp.common.utilities.exceptions;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class ValidationProblemDetails extends ProblemDetails {

	private Map<String,String> validationErrors;
}
