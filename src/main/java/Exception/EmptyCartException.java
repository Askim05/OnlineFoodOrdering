package Exception;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(String msg){
        super(msg);
    }
}
