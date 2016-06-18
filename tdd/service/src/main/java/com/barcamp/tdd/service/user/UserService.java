package com.barcamp.tdd.service.user;

import com.barcamp.tdd.domain.user.User;
import com.barcamp.tdd.service.user.dto.UserCreationDto;

/**
 * Author: Arthur Asatryan
 * Company: SFL LLC
 * Date: 6/18/16
 * Time: 11:29 PM
 */
public interface UserService {
    User create(final UserCreationDto dto);

    User getById(final Long id);
}
