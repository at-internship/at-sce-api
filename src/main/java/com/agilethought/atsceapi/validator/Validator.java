package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.model.User;

public interface Validator<E> {
	public void validate(E object);
}
