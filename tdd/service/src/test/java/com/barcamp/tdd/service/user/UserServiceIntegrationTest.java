package com.barcamp.tdd.service.user;

import com.barcamp.tdd.domain.user.User;
import com.barcamp.tdd.service.user.dto.UserCreationDto;
import com.barcamp.tdd.test.AbstractServiceIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/19/16
 * Time: 12:11 AM
 */
@Component
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest {

    //region Dependencies
    @Autowired
    private UserService userService;
    //endregion

    //region Constructors
    public UserServiceIntegrationTest() {
    }
    //endregion

    //region Public methods
    @Test
    public void testCreate() {
        // given we have user creation dto
        final UserCreationDto dto = getServiceIntegrationTestHelper().buildUserCreationDto();
        // when we try to create a new user and the service returns persisted user
        final User result = userService.create(dto);
        // then
        getServiceIntegrationTestHelper().assertPersistentUserAndCreationDto(dto, result);
    }

    @Test
    public void testGetById() {
        // given we have already persisted user
        final User user = getServiceIntegrationTestHelper().buildAndPersistUser();
        // when we try to get user by id
        final User result = userService.getById(user.getId());
        // then the returned result should not be null
        assertNotNull(result);
        // and returned result should be equal to already persisted user
        assertEquals(user, result);
    }
    //endregion

}
