package com.biacode.testing.repository;

import com.biacode.testing.domain.AccessToken;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 6:37 PM
 */
public interface AccessTokenRepository {

    AccessToken findByToken(final String token);

}
