package com.barcamp.tdd.service.user.impl;

import com.barcamp.tdd.domain.user.User;
import com.barcamp.tdd.repository.user.UserRepository;
import com.barcamp.tdd.service.user.UserService;
import com.barcamp.tdd.service.user.dto.UserCreationDto;
import com.barcamp.tdd.service.user.exception.UserAlreadyExistsForEmailException;
import com.barcamp.tdd.service.user.exception.UserNotFoundForIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/18/16
 * Time: 11:30 PM
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    //region Dependencies
    @Autowired
    private UserRepository userRepository;
    //endregion

    //region Constructors
    public UserServiceImpl() {
        LOGGER.debug("Initializing user service");
    }
    //endregion

    //region Public methods
    @Transactional
    @Override
    public User create(final UserCreationDto dto) {
        LOGGER.debug("Ensuring the the dto - {} is valid.", dto);
        assertUserCreationDto(dto);
        LOGGER.debug("Ensuring that there is no user exists for email - {}", dto.getEmail());
        assertUserNotExistsForEmail(dto);
        LOGGER.debug("Building user domain model for dto - {}", dto);
        final User user = buildUserFromUserCreationDto(dto);
        LOGGER.debug("Persisting user - {}", user);
        final User persistentUser = userRepository.save(user);
        LOGGER.debug("Successfully persisted user - {}", persistentUser);
        return persistentUser;
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(final Long id) {
        Assert.notNull(id, "The user id should not be null");
        LOGGER.debug("Getting user for id - {}", id);
        final User user = userRepository.findOne(id);
        LOGGER.debug("Ensuring user - {} is not null for id - {}", user, id);
        assertUserNotNullForId(id, user);
        LOGGER.debug("Successfully retrieved user - {}", user);
        return user;
    }
    //endregion

    //region Utility methods
    private void assertUserCreationDto(final UserCreationDto dto) {
        Assert.notNull(dto, "The user creation dto should not be null");
        Assert.notNull(dto.getEmail(), "The email should not be null");
        Assert.notNull(dto.getPassword(), "The email should not be null");
    }

    private void assertUserNotExistsForEmail(final UserCreationDto dto) {
        final User user = userRepository.findByEmail(dto.getEmail());
        if (user != null) {
            LOGGER.error("User wit email - {} already exists.", dto.getEmail());
            throw new UserAlreadyExistsForEmailException("User with email already exists.", dto.getEmail());
        }
    }

    private User buildUserFromUserCreationDto(final UserCreationDto dto) {
        return new User(dto.getEmail(), dto.getPassword());
    }

    private void assertUserNotNullForId(final Long id, final User user) {
        if (user == null) {
            LOGGER.error("Can not find user for id - {}", id);
            throw new UserNotFoundForIdException("Can not find user for id", id);
        }
    }
    //endregion
}
