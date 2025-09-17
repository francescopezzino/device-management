package com.company.devicemanagement.exception;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

@Getter
@Setter
public class ErrorModel {

    private String code;
    private String message;
}
