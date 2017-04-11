package com.fruit.Interceptor;

import com.fruit.annotation.Limit;
import com.fruit.utils.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by TanLiu on 2017/4/11.
 */
public class CheckAuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod= (HandlerMethod) handler;
        Limit limit = handlerMethod.getMethodAnnotation(Limit.class);
        if(limit!=null){
            RequestMapping methodAnnotation = handlerMethod.getMethodAnnotation(RequestMapping.class);
            RequestMapping annotation = handlerMethod.getBean().getClass().getAnnotation(RequestMapping.class);
            String url=annotation.value()[0]+methodAnnotation.value()[0];
            String urls= (String) request.getSession().getAttribute("urls");
            if(urls.contains(url)){
                return true;
            }else{
                JsonResult jsonResult=new JsonResult(999,"没有权限");
                PrintWriter writer = response.getWriter();
                writer.print(jsonResult.toString());
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
