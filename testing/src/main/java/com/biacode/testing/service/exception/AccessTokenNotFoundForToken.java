package com.biacode.testing.service.exception;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 6:44 PM
 */
public final class AccessTokenNotFoundForToken extends RuntimeException {

    private static final long serialVersionUID = 4941592015002948044L;

    private final String token;

    public AccessTokenNotFoundForToken(final String message, final String token) {
        super(message);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
