package com.biacode.testing.service.impl;

import com.biacode.testing.domain.AccessToken;
import com.biacode.testing.service.AccessTokenService;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 3:50 PM
 */
public class AccessTokenServiceImplTest {

    //region Test subject
    private final AccessTokenService accessTokenService = new AccessTokenServiceImpl();
    //endregion

    //region Test methods

    //region expired
    @Test
    public void testExpiresWhenTheAccessTokenIsNotExpired() {
        // test data
        final String token = UUID.randomUUID().toString();
        final DateTime expires = DateTime.now().plusDays(3);
        final AccessToken accessToken = new AccessToken(token, expires);
        // test scenario
        final boolean isExpired = accessTokenService.expired(accessToken);
        // assertions
        assertFalse(isExpired);
    }

    @Test
    public void testExpiresWhenTheAccessTokenIsExpired() {
        // test data
        final String token = UUID.randomUUID().toString();
        final DateTime expires = DateTime.now().minusHours(2);
        final AccessToken accessToken = new AccessToken(token, expires);
        // test scenario
        final boolean isExpired = accessTokenService.expired(accessToken);
        // assertions
        assertTrue(isExpired);
    }
    //endregion

    //endregion

}