package com.abstractionizer.login.jwt.exceptions;

import com.abstractionizer.login.jwt.enums.BaseError;

public class InvalidCredentialException extends CustomException{

    public InvalidCredentialException(BaseError baseError) {
        super(baseError);
    }

    public InvalidCredentialException(BaseError baseError, String details){
        super(baseError, details);
    }
}
