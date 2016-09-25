package com.pmease.commons.hibernate;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Singleton
public class DefaultEntityValidator implements EntityValidator {

	protected final Validator validator;
	
	public DefaultEntityValidator(Validator validator) {
		this.validator = validator;
	}
	
	@Override
	public void validate(AbstractEntity entity) {
		for (ConstraintViolation<?> violation: validator.validate(entity)) {
			reportError(entity, violation);
		}
	}

	protected void reportError(AbstractEntity entity, ConstraintViolation<?> violation) {
		String errorInfo = String.format("Error validating entity {entity class: %s, entity id: %d, entity property: %s, error message: %s}", 
				entity.getClass(), entity.getId(), violation.getPropertyPath().toString(), violation.getMessage());
		throw new RuntimeException(errorInfo);
	}
	
}
