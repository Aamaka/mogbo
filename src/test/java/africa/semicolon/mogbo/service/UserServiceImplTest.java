package africa.semicolon.mogbo.service;

import africa.semicolon.mogbo.data.repository.UserRepository;
import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import africa.semicolon.mogbo.exceptions.DuplicateException;
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
        try {
            register();

        }catch (DuplicateException e){
            assertEquals(1, userRepository.count());
        }



    }

    private void register() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("funmi@gmail.com");
        request.setFirstName("Funmi");
        request.setLastName("Ola");
        request.setPassword("hello");
        userService.registerUser(request);
    }

    @Test
    public void duplicateEmailThrowsExceptionTest(){
        register();
        assertThrows(DuplicateException.class, this::register);
        try {
            register();
        }catch (DuplicateException ex){
            assertEquals("email exist", ex.getMessage());
        }
    }
}