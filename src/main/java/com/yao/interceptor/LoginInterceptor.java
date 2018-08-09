package com.yao.interceptor;

import com.yao.entity.IP;
import com.yao.repository.IPRepository;
import com.yao.util.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private IPRepository ipRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入了拦截器方法了。。。。。。");
        String ip = IPUtils.getIpAddress(request);
		System.out.println(ip);

		if(ip.contains(",")){
		    ip = ip.split(",")[0];
        }

        List<IP> list = ipRepository.findAll();
        for(IP i : list){
			System.out.println(i);
            if(i.getIp().equals(ip)){
				System.out.println("IP匹配到了");
                return true;
            }
        }
        response.sendRedirect("/");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    public static void main(String[] args) {
        String str = "183.197.78.191123.114.207.109";
        String[] strss = str.split(",");
        if(strss.length>1){
            System.out.println(strss[0]);
        }


    }

}
