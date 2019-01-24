package com.github.lostizalith.velka.account.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Account currency validator annotation.
 */
@Documented
@Constraint(validatedBy = AccountCurrencyValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountCurrencyConstraint {

    /**
     * Default validation message.
     *
     * @return validation message
     */
    String message() default "{com.github.lostizalith.velka.account.currency}";

    /**
     * Necessary class groups.
     *
     * @return class groups
     */
    Class<?>[] groups() default {};

    /**
     * Useful additional payload.
     *
     * @return return payload data
     */
    Class<? extends Payload>[] payload() default {};
}
