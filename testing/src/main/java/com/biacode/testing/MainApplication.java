package com.biacode.testing;

import com.biacode.testing.domain.AccessToken;
import com.biacode.testing.service.AccessTokenService;
import com.biacode.testing.service.impl.AccessTokenServiceImpl;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 3:03 PM
 */
public class MainApplication {

    public static void main(String[] args) {
        final AccessToken accessToken = new AccessToken(UUID.randomUUID().toString(), DateTime.now().plusDays(3));
        if (accessTokenService().expired(accessToken)) {
            System.out.println("The access token - " + accessToken + " is expired.");
        } else {
            System.out.println("The access token - " + accessToken + " is not expired.");
        }
    }

    private static AccessTokenService accessTokenService() {
        return new AccessTokenServiceImpl();
    }
}
