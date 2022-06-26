package africa.semicolon.mogbo.service;

import africa.semicolon.mogbo.dto.requests.LoginUserRequest;
import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import africa.semicolon.mogbo.dto.responses.RegisterUserResponse;
import africa.semicolon.mogbo.dto.responses.LoginUserResponse;

public interface UserService {
    RegisterUserResponse registerUser (RegisterUserRequest request);

    LoginUserResponse login (LoginUserRequest request);
}
