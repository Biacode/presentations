package com.barcamp.tdd.service.user.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/18/16
 * Time: 11:32 PM
 */
public class UserCreationDto implements Serializable {
    private static final long serialVersionUID = -3795826265205920863L;

    //region Properties
    private String email;

    private String password;
    //endregion

    //region Constructors
    public UserCreationDto() {
    }

    public UserCreationDto(final String email, final String password) {
        this.email = email;
        this.password = password;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCreationDto)) {
            return false;
        }
        final UserCreationDto that = (UserCreationDto) o;
        return new EqualsBuilder()
                .append(email, that.email)
                .append(password, that.password)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(email)
                .append(password)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("password", password)
                .toString();
    }
    //endregion

    //region Properties getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
    //endregion
}
