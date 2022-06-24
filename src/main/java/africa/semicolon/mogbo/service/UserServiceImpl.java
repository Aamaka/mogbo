package africa.semicolon.mogbo.service;

import africa.semicolon.mogbo.data.model.User;
import africa.semicolon.mogbo.data.repository.UserRepository;
import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import africa.semicolon.mogbo.dto.responses.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User use = new User();
        use.setEmail(request.getEmail());
        use.setFirstName(request.getFirstName());
        use.setLastName(request.getLastName());
        use.setPassword(request.getPassword());
        return null;
    }
}
