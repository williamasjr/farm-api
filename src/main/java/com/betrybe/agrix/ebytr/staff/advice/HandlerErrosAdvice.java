package com.betrybe.agrix.ebytr.staff.advice;

import com.betrybe.agrix.ebytr.staff.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * comment.
 */

@RestControllerAdvice
public class HandlerErrosAdvice {

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<String> handleException(CustomException exceptionNotFound) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionNotFound.getMessage());
  }

}
