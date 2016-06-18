package com.barcamp.tdd.service.user.exception;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/18/16
 * Time: 11:46 PM
 */
public class UserAlreadyExistsForEmailException extends RuntimeException {
    private static final long serialVersionUID = -1885175502484780110L;

    //region Properties
    private final String email;
    //endregion

    //region Constructors
    public UserAlreadyExistsForEmailException(final String message, final String email) {
        super(message);
        this.email = email;
    }
    //endregion

    //region Properties getters and setters
    public String getEmail() {
        return email;
    }
    //endregion
}
