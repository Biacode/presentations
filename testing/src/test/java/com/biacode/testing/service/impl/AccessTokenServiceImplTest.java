package com.biacode.testing.service.impl;

import com.biacode.testing.domain.AccessToken;
import com.biacode.testing.repository.AccessTokenRepository;
import com.biacode.testing.service.AccessTokenService;
import com.biacode.testing.service.exception.AccessTokenExpiredRuntimeException;
import com.biacode.testing.service.exception.AccessTokenNotFoundForToken;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 3:50 PM
 */
@RunWith(EasyMockRunner.class)
public class AccessTokenServiceImplTest extends EasyMockSupport {

    //region Test subject
    @TestSubject
    private final AccessTokenService accessTokenService = new AccessTokenServiceImpl();

    @Mock
    private AccessTokenRepository accessTokenRepository;
    //endregion

    //region Test methods

    //region expired
    @Test
    public void testExpiredWithInvalidArguments() {
        // test data
        // test scenario
        try {
            accessTokenService.expired(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        // assertions
    }

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

    //region remainingDays
    @Test
    public void testRemainingDaysWithInvalidArguments() {
        // test data
        // test scenario
        try {
            accessTokenService.remainingDays(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        // assertions
    }

    @Test
    public void testRemainingDaysWhenAccessTokenExpired() {
        // test data
        final String token = UUID.randomUUID().toString();
        final DateTime expiration = DateTime.now().minusDays(7);
        final AccessToken accessToken = new AccessToken(token, expiration);
        // test scenario
        try {
            accessTokenService.remainingDays(accessToken);
            fail();
        } catch (final AccessTokenExpiredRuntimeException ex) {
            assertNotNull(ex);
            assertEquals(accessToken, ex.getAccessToken());
        }
        // assertions
    }

    @Test
    public void testRemainingDaysWhenEqualsTo7() {
        // test data
        final int expectedDays = 7;
        final String token = UUID.randomUUID().toString();
        final DateTime expiration = DateTime.now().plusDays(expectedDays);
        final AccessToken accessToken = new AccessToken(token, expiration);
        // test scenario
        final int remainingDays = accessTokenService.remainingDays(accessToken);
        // assertions
        assertEquals(expectedDays - 1, remainingDays);
    }
    //endregion

    //region getByToken
    @Test
    public void testGetByTokenWithInvalidArguments() {
        // test data
        // test scenario
        try {
            accessTokenService.getByToken(null);
            fail();
        } catch (final IllegalArgumentException ignore) {
        }
        // assertions
    }

    @Test
    public void testGetByTokenWhenAccessTokenDoesNotExists() {
        // test data
        final String token = UUID.randomUUID().toString();
        // test scenario
        expect(accessTokenRepository.findByToken(token)).andReturn(null);
        try {
            accessTokenService.getByToken(token);
            fail();
        } catch (final AccessTokenNotFoundForToken ex) {
            assertNotNull(ex);
            assertEquals(token, ex.getToken());
        }
        // assertions
    }
    //endregion

    //endregion

}