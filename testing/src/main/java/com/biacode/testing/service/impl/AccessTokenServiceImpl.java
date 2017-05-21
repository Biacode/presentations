package com.biacode.testing.service.impl;

import com.biacode.testing.domain.AccessToken;
import com.biacode.testing.repository.AccessTokenRepository;
import com.biacode.testing.service.AccessTokenService;
import com.biacode.testing.service.exception.AccessTokenExpiredRuntimeException;
import com.biacode.testing.service.exception.AccessTokenNotFoundForToken;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 3:15 PM
 */
public class AccessTokenServiceImpl implements AccessTokenService {

    //region Dependencies
    private AccessTokenRepository accessTokenRepository;
    //endregion

    //region Public methods
    @Override
    public boolean expired(final AccessToken accessToken) {
        assertAccessTokenNotNull(accessToken);
        return accessToken.getExpiration().isBeforeNow();
    }

    @Override
    public int remainingDays(final AccessToken accessToken) {
        assertAccessTokenNotNull(accessToken);
        assertAccessTokenNotExpired(accessToken);
        return new Interval(DateTime.now(), accessToken.getExpiration()).toPeriod().getDays();
    }

    @Override
    public AccessToken getByToken(final String token) {
        assertTokenNotNull(token);
        final AccessToken accessToken = accessTokenRepository.findByToken(token);
        if (accessToken == null) {
            throw new AccessTokenNotFoundForToken("Can not find access token", token);
        }
        return null;
    }
    //endregion

    //region Utility methods
    private void assertAccessTokenNotNull(final AccessToken accessToken) {
        if (accessToken == null) {
            throw new IllegalArgumentException("The access token should not be null");
        }
    }

    private void assertAccessTokenNotExpired(final AccessToken accessToken) {
        if (expired(accessToken)) {
            throw new AccessTokenExpiredRuntimeException("Access token expired", accessToken);
        }
    }

    private void assertTokenNotNull(final String token) {
        if (token == null) {
            throw new IllegalArgumentException("The access token should not be null");
        }
    }
    //endregion
}
