package com.devopsjavaspring.exceptions;

import com.stripe.exception.AuthenticationException;

/**
 * Created by stephenasamoah on 9/14/16.
 */
public class StripeException extends RuntimeException {
    public StripeException(Throwable e) {
        super(e);
    }
}
