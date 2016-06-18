package com.barcamp.tdd.service.user.exception;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/19/16
 * Time: 12:26 AM
 */
public class UserNotFoundForIdException extends RuntimeException {
    private static final long serialVersionUID = -7980996165478861321L;

    //region Properties
    private final Long id;
    //endregion

    //region Constructors
    public UserNotFoundForIdException(final String message, final Long id) {
        super(message);
        this.id = id;
    }
    //endregion

    //region Properties getters and setters
    public Long getId() {
        return id;
    }
    //endregion
}
