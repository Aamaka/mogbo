package africa.semicolon.mogbo.service;

import africa.semicolon.mogbo.data.model.User;
import africa.semicolon.mogbo.data.repository.UserRepository;
import africa.semicolon.mogbo.dto.requests.LoginUserRequest;
import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import africa.semicolon.mogbo.dto.responses.LoginUserResponse;
import africa.semicolon.mogbo.dto.responses.RegisterUserResponse;
import africa.semicolon.mogbo.exceptions.EmailNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(savedUser.getEmail());
        response.setDateCreated(DateTimeFormatter.ofPattern("EEEE, dd/MM/ yyyy,  hh:mm, a").
                format(savedUser.getDateTime()));
        return response;
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
      Optional<User> user= userRepository.findUserByEmail(request.getEmail());
            if(user.isPresent()) {
                if (user.get().getPassword().equals(request.getPassword())) {
                    LoginUserResponse response = new LoginUserResponse();
                    response.setMessage("Welcome back " + user.get().getFirstName());
                    response.setLastSeen(DateTimeFormatter.ofPattern("EEEE, dd/MM/ yyyy,  hh:mm, a").
                            format(user.get().getDateTime()));
                    return response;
                }
                else throw new EmailNotFoundException("not found");
            }
         throw new EmailNotFoundException("not found");
    }



}
