package com.biacode.testing.service;

import com.biacode.testing.domain.AccessToken;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 3:03 PM
 */
public interface AccessTokenService {
    boolean expired(final AccessToken accessToken);
}
