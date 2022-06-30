package africa.semicolon.mogbo.controller;

import africa.semicolon.mogbo.dto.requests.CreatedPartyRequest;
import africa.semicolon.mogbo.dto.requests.LoginUserRequest;
import africa.semicolon.mogbo.dto.requests.RegisterUserRequest;
import africa.semicolon.mogbo.dto.responses.ApiResponse;
import africa.semicolon.mogbo.dto.responses.LoginUserResponse;
import africa.semicolon.mogbo.dto.responses.RegisterUserResponse;
import africa.semicolon.mogbo.exceptions.DuplicateException;
import africa.semicolon.mogbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest request) {
        try {
            var serviceResponse = userService.registerUser(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DuplicateException ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/login/user")
    public LoginUserResponse login(@RequestBody LoginUserRequest request) {
        return userService.login(request);
    }


    @PostMapping("/party")
    public ResponseEntity<?> createParty(@RequestBody CreatedPartyRequest request) {
        try {
            var serviceResponse = userService.addParty(request);
            ApiResponse response = new ApiResponse(true, serviceResponse);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DuplicateException ex) {
            ApiResponse response = new ApiResponse(false, ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }
}