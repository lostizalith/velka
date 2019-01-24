package com.github.lostizalith.velka.account.validator;

import com.github.lostizalith.velka.account.entity.AccountCurrency;
import com.github.lostizalith.velka.global.utils.EnumParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Account currency custom validator.
 */
public class AccountCurrencyValidator implements ConstraintValidator<AccountCurrencyConstraint, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return EnumParser.findOptionalEnumValue(AccountCurrency.values(), value).isPresent();
    }

    @Override
    public void initialize(final AccountCurrencyConstraint constraintAnnotation) {
        // avoid implementation.
    }
}
