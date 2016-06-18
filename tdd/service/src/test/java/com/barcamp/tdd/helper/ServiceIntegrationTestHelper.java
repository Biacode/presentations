package com.barcamp.tdd.helper;

import com.barcamp.tdd.domain.user.User;
import com.barcamp.tdd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/19/16
 * Time: 12:03 AM
 */
@Component
public final class ServiceIntegrationTestHelper extends ServiceCommonTestHelper {

    //region Dependencies
    @Autowired
    private UserService userService;
    //endregion

    //region Constructors
    public ServiceIntegrationTestHelper() {
    }
    //endregion

    //region Public methods

    //region User
    public User buildAndPersistUser() {
        return userService.create(buildUserCreationDto());
    }
    //endregion

    //endregion

}
