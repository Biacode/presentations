package com.biacode.testing.service.impl;

import com.biacode.testing.domain.AccessToken;
import com.biacode.testing.service.AccessTokenService;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 3:15 PM
 */
public class AccessTokenServiceImpl implements AccessTokenService {

    @Override
    public boolean expired(final AccessToken accessToken) {
        return accessToken.getExpiration().isBeforeNow();
    }
}
