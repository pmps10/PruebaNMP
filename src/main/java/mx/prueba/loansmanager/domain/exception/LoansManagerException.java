package mx.prueba.loansmanager.domain.exception;

public class LoansManagerException extends RuntimeException {

    public LoansManagerException(final String message) {
        super(message);
    }

    public LoansManagerException(final Throwable cause) {
        super(cause);
    }
}
