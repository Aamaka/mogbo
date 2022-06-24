package africa.semicolon.mogbo.service;

import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import africa.semicolon.mogbo.dto.responses.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse registerUser (RegisterUserRequest request);
}
