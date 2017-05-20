package bootdemo.hanlder;

import bootdemo.config.RedisConfig;
import bootdemo.entity.Result;
import bootdemo.exception.ResultException;
import bootdemo.utils.ResultUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by David on 2017/5/18.
 */

@ControllerAdvice
public class ExceptionHandle {
    private static Logger logger = Logger.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof ResultException){
            ResultException resultException = (ResultException) e;
            return ResultUtils.getErrorResult(resultException.code,resultException.getMessage());
        }
        logger.error(e.getCause()+e.getMessage());
        return ResultUtils.getErrorResult(-1,e.getMessage());
    }
}
