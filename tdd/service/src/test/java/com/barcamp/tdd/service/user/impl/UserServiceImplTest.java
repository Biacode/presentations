package com.barcamp.tdd.service.user.impl;

import com.barcamp.tdd.domain.user.User;
import com.barcamp.tdd.repository.user.UserRepository;
import com.barcamp.tdd.service.user.UserService;
import com.barcamp.tdd.service.user.dto.UserCreationDto;
import com.barcamp.tdd.service.user.exception.UserAlreadyExistsForEmailException;
import com.barcamp.tdd.service.user.exception.UserNotFoundForIdException;
import com.barcamp.tdd.test.AbstractServiceImplTest;
import org.apache.commons.lang3.SerializationUtils;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/18/16
 * Time: 11:26 PM
 */
public class UserServiceImplTest extends AbstractServiceImplTest {

    //region Test subject and mocks
    @TestSubject
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;
    //endregion

    //region Constructors
    public UserServiceImplTest() {
    }
    //endregion

    //region Test methods

    //region initial
    @Test
    public void testHaveUserService() {
        resetAll();
        // test data
        // expectations
        replayAll();
        // run test scenario
        assertNotNull(userService);
        verifyAll();
    }

    @Test
    public void testHaveUserRepository() {
        resetAll();
        // test data
        // expectations
        replayAll();
        // run test scenario
        assertNotNull(userRepository);
        verifyAll();
    }
    //endregion

    //region create
    @Test
    public void testCreateWithInvalidArguments() {
        resetAll();
        // test data
        final UserCreationDto validDto = getServiceImplTestHelper().buildUserCreationDto();
        UserCreationDto invalidDto;
        // expectations
        replayAll();
        // run test scenario
        // the dto should not be null
        invalidDto = null;
        try {
            userService.create(invalidDto);
            fail("exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
            // expected
        }
        // the email should not be null
        invalidDto = SerializationUtils.clone(validDto);
        invalidDto.setEmail(null);
        try {
            userService.create(invalidDto);
            fail("exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
            // expected
        }
        // the password should not be null
        invalidDto = SerializationUtils.clone(validDto);
        invalidDto.setPassword(null);
        try {
            userService.create(invalidDto);
            fail("exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
            // expected
        }
        verifyAll();
    }

    @Test
    public void testCreateWhenUserAlreadyExists() {
        resetAll();
        // test data
        final UserCreationDto dto = getServiceImplTestHelper().buildUserCreationDto();
        final User user = getServiceImplTestHelper().buildUser();
        // expectations
        expect(userRepository.findByEmail(dto.getEmail())).andReturn(user);
        replayAll();
        // run test scenario
        try {
            userService.create(dto);
            fail("exception should be thrown");
        } catch (final UserAlreadyExistsForEmailException ex) {
            // expected
            assertNotNull(ex);
            assertEquals(dto.getEmail(), ex.getEmail());
        }
        verifyAll();
    }

    @Test
    public void testCreate() {
        resetAll();
        // test data
        final UserCreationDto dto = getServiceImplTestHelper().buildUserCreationDto();
        // expectations
        expect(userRepository.findByEmail(dto.getEmail())).andReturn(null);
        expect(userRepository.save(isA(User.class))).andAnswer(() -> (User) getCurrentArguments()[0]);
        replayAll();
        // run test scenario
        final User result = userService.create(dto);
        getServiceImplTestHelper().assertPersistentUserAndCreationDto(dto, result);
        verifyAll();
    }
    //endregion

    //region getById
    @Test
    public void testGetByIdWithInvalidArguments() {
        resetAll();
        // test data
        // expectations
        replayAll();
        // run test scenario
        try {
            userService.getById(null);
            fail("exception should be thrown");
        } catch (final IllegalArgumentException ignore) {
            // expected
        }
        verifyAll();
    }

    @Test
    public void testGetByIdWhenUserNotFound() {
        resetAll();
        // test data
        final Long id = 7L;
        // expectations
        expect(userRepository.findOne(id)).andReturn(null);
        replayAll();
        // run test scenario
        try {
            userService.getById(id);
            fail("exception should be thrown");
        } catch (final UserNotFoundForIdException ex) {
            // expected
            assertNotNull(ex);
            assertEquals(id, ex.getId());
        }
        verifyAll();
    }

    @Test
    public void testGetById() {
        resetAll();
        // test data
        final User user = getServiceImplTestHelper().buildUser();
        final Long id = user.getId();
        // expectations
        expect(userRepository.findOne(id)).andReturn(user);
        replayAll();
        // run test scenario
        userService.getById(id);
        verifyAll();
    }
    //endregion

    //endregion

}
