package com.example.pentaproject.exception;

import com.example.pentaproject.dtos.GetJwtResponse;
import com.example.pentaproject.dtos.GetResponse;
import com.example.pentaproject.dtos.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandle extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<> (new MessageResponse("User is not Found with provided credentials"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<?> handleUserNotActiveException(UserNotActiveException exception){
        return new ResponseEntity<>(new MessageResponse("User is Not Active. Please Contact Admin"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeachersAndStudentsNotFoundException.class)
    public ResponseEntity<?> handlePersonsNotFoundException(TeachersAndStudentsNotFoundException exception){
        return ResponseEntity.ok(new GetResponse("Teachers and Students Not Found", null));
    }

    @ExceptionHandler(MailUnsupportedException.class)
    public ResponseEntity<?> handleMailUnsupportedException(MailUnsupportedException excception){
        return new ResponseEntity<>(new GetResponse("Mail not Sent"+" Messaging Exception" + " or Encoding Exception", null), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MailNotSentException.class)
    public ResponseEntity<?> handleMailUnsupportedException(MailNotSentException excception){
        return new ResponseEntity<>(new GetResponse("Mail not Sent"+" Please try again", null), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UserActionNotAccepted.class)
    public ResponseEntity<?> handleUserActionNotAcceptedException(UserActionNotAccepted exception){
        return new ResponseEntity<>(new GetResponse("User Action is Not Permisible", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRoleInfoInputException.class)
    public ResponseEntity<?> handleRoleInfoInputException(InvalidRoleInfoInputException exception){
        return new ResponseEntity<>(new GetResponse("Role Info Input is Invalid", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEventNameInputException.class)
    public ResponseEntity<?> handleInvalidEventName(InvalidEventNameInputException exception){
        return new ResponseEntity<>(new GetResponse("Event Name is Invalid", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidProfileRequestTeacherException.class)
    public ResponseEntity<?> handleInvalidProfileRequestException(InvalidProfileRequestTeacherException exception){
        return new ResponseEntity<>(new GetResponse("Invalid Profile Requests from Teacher", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdvisorNotFoundException.class)
    public ResponseEntity<?> handleAdvisorNotFoundException(AdvisorNotFoundException exception){
        return new ResponseEntity<>(new GetResponse("Teacher is Permissible to Perform the Action", null), HttpStatus.BAD_REQUEST);
    }
}
