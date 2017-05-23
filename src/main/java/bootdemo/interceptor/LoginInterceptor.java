package bootdemo.interceptor;

import bootdemo.entity.ResultCode;
import bootdemo.exception.ResultException;
import bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by David on 2017/5/23.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final static String SECRET_KEY = "secretKey";
    private final static String uid ="uid";

    @Autowired
    private UserService service;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String secretKey = request.getParameter(SECRET_KEY);
        String userName = request.getParameter(uid);
        if(!StringUtils.isEmpty(secretKey) && !StringUtils.isEmpty(userName)){
            String realKey = service.getSecretKey(userName);
            if(realKey != null && realKey.equals(secretKey)){
                return true;
            }
        }
        throw new ResultException(ResultCode.SECRET_KEY_ERROR,"密钥错误，请重新登录");

    }


}
