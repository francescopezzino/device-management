package com.company.devicemanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> hadleFieldValidattion(MethodArgumentNotValidException manv) {
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModels = null;
        List<FieldError> fieldErrorlIST = manv.getBindingResult().getFieldErrors();
        for (FieldError fe: fieldErrorlIST) {
            logger.debug("inside field validation ", fe.getField(), fe.getDefaultMessage());
            logger.info("inside field validation ", fe.getField(), fe.getDefaultMessage());
            errorModels = new ErrorModel();
            errorModels.setCode(fe.getField());
            errorModels.setMessage(fe.getDefaultMessage());
            errorModelList.add(errorModels);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex) {
        for(ErrorModel error: bex.getErrors()) {
            logger.debug("BusinessException is thronw level debug {}, {}", error.getCode(), error.getMessage());
            logger.info("BusinessException is thronw level info {}, {}", error.getCode(),error.getMessage());
            logger.warn("BusinessException is thronw level warn {}, {}", error.getCode(), error.getMessage());
            logger.error("BusinessException is thronw level error {}, {}", error.getCode(),error.getMessage());
        }

        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST );
    }
}