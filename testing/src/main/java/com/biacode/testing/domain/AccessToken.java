package com.biacode.testing.domain;

import org.joda.time.DateTime;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 2:59 PM
 */
public class AccessToken {

    private String accessToken;

    private DateTime expiration;

    public AccessToken() {
        // Default constructor
    }

    public AccessToken(final String accessToken, final DateTime expiration) {
        this.accessToken = accessToken;
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public DateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(final DateTime expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "AccessToken{" + "accessToken='" + accessToken + '\'' +
                ", expiration=" + expiration +
                '}';
    }
}
