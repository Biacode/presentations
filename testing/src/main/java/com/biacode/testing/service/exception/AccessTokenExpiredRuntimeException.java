package com.biacode.testing.service.exception;

import com.biacode.testing.domain.AccessToken;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 5:16 PM
 */
public final class AccessTokenExpiredRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 4516629926728726361L;

    private final AccessToken accessToken;

    public AccessTokenExpiredRuntimeException(final String message, final AccessToken accessToken) {
        super(message);
        this.accessToken = accessToken;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }
}
