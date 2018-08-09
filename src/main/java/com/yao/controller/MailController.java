package com.yao.controller;

import com.yao.entity.IP;
import com.yao.repository.IPRepository;
import com.yao.util.DateUtil;
import com.yao.util.IPUtils;
import com.yao.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
public class MailController {

    @Autowired
    private IPRepository ipRepository;

    @Value(value = "${mail.myEmailAccount}")
    public String myEmailAccount;

    @Value(value = "${mail.myEmailPassword}")
    public String myEmailPassword;

    @Value(value = "${mail.myEmailSMTPHost}")
    public String myEmailSMTPHost;

    @Value(value = "${mail.receiveMailAccount}")
    public String receiveMailAccount;


    //一个方法不加ResponseBody注解而直接返回字符串的话会去寻找叫 那个字符串值的 的那个html页面！
    @ResponseBody
    @RequestMapping("/mail")
    public String mail(HttpServletRequest request,String name) throws  Exception{

    System.out.println("来到了发送邮件的方法。。。");

    String ip = IPUtils.getIpAddress(request);



    String content = "IP为 "+IPUtils.getIpAddress(request)+" 的用户在 "+DateUtil.getCurrentDateStr2()+"成功登录了后台管理系统";
    //说明白名单内有这个IP，允许登录
        //为了让前台快速的弹出那个窗口，这里就new了一个线程，弄成异步的了。
       new T(content, myEmailAccount, myEmailPassword, myEmailSMTPHost, receiveMailAccount).start();
       return null;
    }

    class T extends Thread{

        private String content;

        public String myEmailAccount;

        public String myEmailPassword;

        public String myEmailSMTPHost;

        public String receiveMailAccount;

        public T(String content, String myEmailAccount, String myEmailPassword, String myEmailSMTPHost, String receiveMailAccount) {
           super();
            this.content = content;
            this.myEmailAccount = myEmailAccount;
            this.myEmailPassword = myEmailPassword;
            this.myEmailSMTPHost = myEmailSMTPHost;
            this.receiveMailAccount = receiveMailAccount;
        }

        @Override
        public void run() {
            try {
                //为了安全，这里就不判断name是否为666了，只要发送了请求就发送邮件。
                MailUtils.send("网站后台系统", "电影网站通知",
                        content, myEmailAccount, myEmailPassword, myEmailSMTPHost, receiveMailAccount);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}