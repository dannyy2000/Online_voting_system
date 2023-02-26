package Africa.semicolon.my_VotingApp.exception;

public class ElectionServiceException extends RuntimeException{
    public ElectionServiceException(String message) {
        super(message);
    }
}
