package uk.co.jimbodev.glasscuttingproblem.exception;

public class InvalidAlgorithmNameException extends RuntimeException {

    public InvalidAlgorithmNameException(String errorMessage){
        super(errorMessage);
    }
}
