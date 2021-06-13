package com.example.leovegas.exception;

import com.example.leovegas.dto.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    ResponseMessage responseMessage = new ResponseMessage();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ResponseMessage> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        responseMessage.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        responseMessage.setMessage("Validation error. Check 'errors' field and Try again.");
        responseMessage.setUri(request.getDescription(true));
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            responseMessage.addValidationError(fieldError.getField(),
                    fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(responseMessage);
    }


    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> AlreadyExist(
            Exception exception,
            WebRequest request) {
        logger.error("Player already exist: ", exception);
        responseMessage.setCode(HttpStatus.BAD_REQUEST.value());
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setUri(request.getDescription(true));
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IsNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> NotExist(
            Exception exception,
            WebRequest request) {
        logger.error("Player not exist: ", exception);
        responseMessage.setCode(HttpStatus.BAD_REQUEST.value());
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setUri(request.getDescription(true));
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalletDoesntExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> walletIsNotExist(
            Exception exception,
            WebRequest request) {
        logger.error("Wallet does not exist!", exception);
        responseMessage.setCode(HttpStatus.BAD_REQUEST.value());
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setUri(request.getDescription(true));
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MinimumBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> minimumBalance(
            Exception exception,
            WebRequest request) {
//        logger.error("Wallet does not exist!", exception);
        responseMessage.setCode(HttpStatus.BAD_REQUEST.value());
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setUri(request.getDescription(true));
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionDuplicatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseMessage> transactionIsNotUnique(
            Exception exception,
            WebRequest request) {
        logger.error("Transaction ID is duplicated!", exception);
        responseMessage.setCode(HttpStatus.BAD_REQUEST.value());
        responseMessage.setMessage(exception.getMessage());
        responseMessage.setUri(request.getDescription(true));
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseMessage> handleUnknownException(
            WebRequest request, Exception exception) {
        logger.error("Unknown Error Occured: " + exception.getMessage());
        responseMessage.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseMessage.setMessage("Unknown Error Occured!");
        responseMessage.setUri(request.getDescription(false));
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}