package com.barcamp.tdd.helper;

import com.barcamp.tdd.domain.user.User;
import com.barcamp.tdd.service.user.dto.UserCreationDto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/19/16
 * Time: 12:03 AM
 */
public class ServiceCommonTestHelper {

    //region Constants
    private static final String EMAIL = "arthur.asatryan@sflpro.com";

    private static final String PASSWORD = "you can't even guess me! :P";
    //endregion

    //region Constructors
    public ServiceCommonTestHelper() {
    }
    //endregion

    //region Public methods

    //region User
    public User buildUser() {
        return new User(7L, EMAIL, PASSWORD);
    }

    public UserCreationDto buildUserCreationDto() {
        return new UserCreationDto(EMAIL, PASSWORD);
    }

    public void assertPersistentUserAndCreationDto(final UserCreationDto dto, final User result) {
        assertNotNull(result);
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getPassword(), result.getPassword());
    }
    //endregion

    //endregion

}
