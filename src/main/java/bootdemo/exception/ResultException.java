package bootdemo.exception;

/**
 * Created by David on 2017/5/18.
 */
public class ResultException extends RuntimeException {

    public int code;

    public ResultException(int code,String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
