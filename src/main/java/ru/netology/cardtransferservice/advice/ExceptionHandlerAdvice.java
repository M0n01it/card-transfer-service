package ru.netology.cardtransferservice.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.cardtransferservice.exception.ConfirmationException;
import ru.netology.cardtransferservice.exception.TransferException;
import ru.netology.cardtransferservice.model.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponse> handleTransferException(TransferException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), 400));
    }

    @ExceptionHandler(ConfirmationException.class)
    public ResponseEntity<ErrorResponse> handleConfirmationException(ConfirmationException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), 400));
    }
} 