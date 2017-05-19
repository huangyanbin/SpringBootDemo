package bootdemo.utils;

import bootdemo.entity.Result;
import bootdemo.entity.ResultCode;

/**
 * Created by David on 2017/5/18.
 */
public class ResultUtils {


    public static Result getErrorResult(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setResult(msg);
        result.setData(null);
        return result;
    }


    public static Result getSuccessResult(Object object){
        Result result = new Result();
        result.setCode(ResultCode.SUC);
        result.setResult("成功");
        result.setData(object);
        return result;
    }
}
