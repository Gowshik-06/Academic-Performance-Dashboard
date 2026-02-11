package com.apda.apda_backend.service;

import com.apda.apda_backend.dto.CreateUserRequest;
import com.apda.apda_backend.entity.User;

public interface UserService {

    User createUser(CreateUserRequest request);

    User getUserById(Long id);
}
