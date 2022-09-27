package com.example.pentaproject.controller;

import com.example.pentaproject.dtos.JwtResponse;
import com.example.pentaproject.dtos.MessageResponse;
import com.example.pentaproject.dtos.SignInRequest;
import com.example.pentaproject.dtos.SignUpRequest;
import com.example.pentaproject.model.Person;
import com.example.pentaproject.service.Impl.PersonDetailsImpl;
import com.example.pentaproject.service.PersonService;
import com.example.pentaproject.service.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    PersonService personService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;


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

        return ResponseEntity.ok(new JwtResponse(jwt,
                personDetails.getId(),
                personDetails.getUsername(),
                personDetails.getEmail(),
                roles));

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

}
