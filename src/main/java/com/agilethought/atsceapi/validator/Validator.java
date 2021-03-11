package com.agilethought.atsceapi.validator;

public interface Validator<E> {
	void validate(E object);
}
