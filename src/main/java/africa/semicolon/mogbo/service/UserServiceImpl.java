package africa.semicolon.mogbo.service;
import africa.semicolon.mogbo.data.model.User;
import africa.semicolon.mogbo.data.repository.UserRepository;
import africa.semicolon.mogbo.dto.requests.LoginUserRequest;
import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import africa.semicolon.mogbo.dto.responses.LoginUserResponse;
import africa.semicolon.mogbo.dto.responses.RegisterUserResponse;
import africa.semicolon.mogbo.exceptions.EmailNotFoundException;
import africa.semicolon.mogbo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
//        User user = Mapper.map(request);
        User user = new User();
        Mapper.map(request, user);

        User savedUser = userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        Mapper.map(savedUser, response);
        return response;
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if(user.getEmail().equals(request.getEmail())){
            if(user.getPassword().equals(request.getPassword())){
                LoginUserResponse response = new LoginUserResponse();
                    response.setMessage("Welcome back " + user.getFirstName());
                    return response;
            }
        }
        throw new EmailNotFoundException("Email not found");
    }

}
