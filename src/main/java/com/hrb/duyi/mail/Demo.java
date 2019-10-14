package com.hrb.duyi.mail;

public class Demo {
    public static void main(String[] args) {
        String toUser = "xxxxxx@qq.com";
        String subject = "测试邮件";
        StringBuffer sb = new StringBuffer();
        String content = "hello";
        sb.append("<!DOCTYPE>" +
                       "<div bgcolor='#f1fcfa' style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; " +
                       "color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'>" +
                         content + "</div>");
        MailUtil.sendMail(toUser,subject,sb.toString());
    }
}
