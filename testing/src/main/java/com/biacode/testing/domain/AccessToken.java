package com.biacode.testing.domain;

import org.joda.time.DateTime;

/**
 * @author Arthur Asatryan
 * @since 5/21/17 2:59 PM
 */
public class AccessToken {

    private String token;

    private DateTime expiration;

    public AccessToken() {
        // Default constructor
    }

    public AccessToken(final String token, final DateTime expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public DateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(final DateTime expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "AccessToken{" + "token='" + token + '\'' +
                ", expiration=" + expiration +
                '}';
    }
}
