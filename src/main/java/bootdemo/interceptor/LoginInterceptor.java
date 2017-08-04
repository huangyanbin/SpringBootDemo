package bootdemo.interceptor;

import bootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by David on 2017/5/23.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    //public final static String SECRET_KEY = "secretKey";
    //private final static String UID ="uid";

    @Autowired
    private UserService service;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("User") != null)
            return true;
        // 跳转登录
        String url = "/login";
        response.sendRedirect(url);
        return false;
    }


}
