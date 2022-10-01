package com.example.pentaproject.controller;

import com.example.pentaproject.dtos.*;
import com.example.pentaproject.exception.MailNotSentException;
import com.example.pentaproject.exception.MailUnsupportedException;
import com.example.pentaproject.exception.UserActionNotAccepted;
import com.example.pentaproject.exception.UserNotFoundException;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.service.Impl.EmailServiceImpl;
import com.example.pentaproject.service.Impl.PersonDetailsImpl;
import com.example.pentaproject.service.PersonService;
import com.example.pentaproject.service.jwt.JwtUtils;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    PersonService personService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmailServiceImpl emailService;


    @PostMapping("auth/signin/person")
    public ResponseEntity<?> authenticatePerson(@Valid @RequestBody SignInRequest signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        PersonDetailsImpl personDetails = (PersonDetailsImpl) authentication.getPrincipal();
        List<String> roles = personDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return new ResponseEntity<>(new GetJwtResponse("Login Successful",new JwtResponse(jwt,
                personDetails.getId(),
                personDetails.getUsername(),
                personDetails.getEmail(),
                roles.get(0).split("_")[1])), HttpStatus.OK);

    }

    @PostMapping("auth/signup/person")
    public ResponseEntity<?> registerPerson(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (personService.isExistsPersonByPhoneNo(signUpRequest.getPhoneNo())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone No is already taken!"));
        }

        if (personService.isExistsPersonEmailId(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }


        // Create new user's account
        Person teacher = new Person(signUpRequest.getUsername(),
                signUpRequest.getPhoneNo(), signUpRequest.getEmail(),
                signUpRequest.getDepartmentName(), signUpRequest.getRole(),
                encoder.encode(signUpRequest.getPassword()), true);

        personService.createPerson(teacher);

        return ResponseEntity.ok(new MessageResponse("Person registered successfully!"));
    }

    @PostMapping("auth/forgot_password")
    public ResponseEntity<?> processForgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest){
        String email = forgotPasswordRequest.getEmail();
        String token = RandomString.make(30);

        if(personService.getPersonByEmailId(email).getRole().equals("Admin")){
            throw new UserActionNotAccepted();
        }
        try {
            personService.updateResetPasswordToken(token, email);
            String resetPasswordLink = forgotPasswordRequest.getLink() + "/reset_password?token=" + token;

            emailService.sendEmail(email, resetPasswordLink);

        }  catch (UnsupportedEncodingException | MessagingException e) {
            throw new MailUnsupportedException();
        } catch (RuntimeException ex) {
            throw new MailNotSentException();
        }

        return ResponseEntity.ok(new MessageResponse("Password Email Sent Successfully"));
    }

    @PostMapping("auth/reset_password")
    public ResponseEntity<?> resetForgotPassWord(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest){
        String token = resetPasswordRequest.getToken();
        String password = resetPasswordRequest.getPassword();

        Person person = personService.getByResetPasswordToken(token);

        if (person == null) {
            throw new UserNotFoundException();
        } else {
            personService.updatePassword(person, password);
        }

        return ResponseEntity.ok(new MessageResponse("Password Reset Successfully"));
    }

}
