public class DAOException extends Exception {
    // this exception will be checked because its super class is Exception.

    public DAOException(String message) {
        super(message);
    }
}