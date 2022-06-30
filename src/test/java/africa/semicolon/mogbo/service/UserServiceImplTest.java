package africa.semicolon.mogbo.service;

import africa.semicolon.mogbo.data.repository.UserRepository;
import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void registerUserTest(){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("funmi@gmail.com");
        request.setFirstName("Funmi");
        request.setLastName("Ola");
        request.setPassword("hello");
        userService.registerUser(request);

        assertEquals(1, userRepository.count());
    }
}