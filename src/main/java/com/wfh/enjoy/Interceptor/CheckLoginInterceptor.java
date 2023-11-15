package com.wfh.enjoy.Interceptor;




import com.alibaba.fastjson.JSON;
import com.wfh.enjoy.utils.BaseContext;
import com.wfh.enjoy.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author wfh
 * @create 2023/3/23 - 16:25
 */
@Slf4j
@Component
public class CheckLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        BaseContext.setShopId((Long) session.getAttribute("shopId"));
        log.info("拦截到请求:{}",uri);
        // 判断登陆状态
        if(uri.startsWith("/enjoy/")){
            if(session.getAttribute("loginUser") != null) {
                log.info("后台登录");
                BaseContext.setCurrentId((Long) session.getAttribute("loginUser"));
                return true;
            }
            else{
                log.info("notLogin");
//             Result<String> error = Result.error("未登录");
//             response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//             response.setCharacterEncoding("UTF-8");
//
//             response.getWriter().write(JSON.toJSONString(error));
                response.sendRedirect("/enjoy/login/login.html");
                return false;
            }
        }
        else if(uri.startsWith("/app/")){
            if (session.getAttribute("user") != null) {
                log.info("移动端登录");
                BaseContext.setCurrentId((Long) session.getAttribute("user"));
                log.info("拦截器设置id:{}",session.getAttribute("user"));
                return true;
            } else {
                log.info("notLogin");
                response.sendRedirect("/app/login.html");
                return false;
            }
        }
        else if(session.getAttribute("user") != null){
            BaseContext.setCurrentId((Long) session.getAttribute("user"));
        }
        else if(session.getAttribute("loginUser") != null){
            BaseContext.setCurrentId((Long) session.getAttribute("loginUser"));
        }
        return true;
    }

}
