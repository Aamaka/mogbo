package africa.semicolon.mogbo.service;
import africa.semicolon.mogbo.data.model.Party;
import africa.semicolon.mogbo.data.model.User;
import africa.semicolon.mogbo.data.repository.UserRepository;
import africa.semicolon.mogbo.dto.requests.CreatedPartyRequest;
import africa.semicolon.mogbo.dto.requests.LoginUserRequest;
import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import africa.semicolon.mogbo.dto.responses.CreatedPartyResponse;
import africa.semicolon.mogbo.dto.responses.LoginUserResponse;
import africa.semicolon.mogbo.dto.responses.RegisterUserResponse;
import africa.semicolon.mogbo.exceptions.DuplicateException;
import africa.semicolon.mogbo.exceptions.EmailNotFoundException;
import africa.semicolon.mogbo.exceptions.UserDoesNotExistException;
import africa.semicolon.mogbo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private  PartyService partyService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PartyService partyService) {
        this.userRepository = userRepository;
        this.partyService = partyService;
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))throw new DuplicateException("email exist");
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

    @Override
    public CreatedPartyResponse addParty(CreatedPartyRequest request) {
        Optional<User> user = userRepository.findUserByEmail(request.getEmail());
        if(user.isEmpty()) throw new UserDoesNotExistException(request.getEmail()+" does not exist");
        User foundUser = user.get();
        Party party = new Party();
        party.setLocation(request.getPartyLocation());
        party.setName(request.getPartyName());
        Party savedParty = partyService.saveParty(party);
        foundUser.getParties().add(savedParty);
        userRepository.save(foundUser);

        CreatedPartyResponse response = new CreatedPartyResponse();
        response.setPartyLocation(savedParty.getLocation());
        response.setCreatedBy(foundUser.getFirstName());
        response.setPartyName(savedParty.getName());
        return response;
    }

}
