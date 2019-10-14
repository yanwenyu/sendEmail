package com.hrb.duyi.mail;

public class Demo2 {
    public static void main(String[] args) {
        String toUser = "382246268@qq.com";
        String subject = "测试邮件";
        StringBuffer sb = new StringBuffer();
        String content = "hello";
        sb.append("<!DOCTYPE>" +
                "<div bgcolor='#f1fcfa' style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; " +
                "color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'>" +
                content + "</div>");
        String path = "C:\\Users\\Administrator\\Desktop\\eee\\IMG_4442.JPG";
        AccessoryMailUtil.sendMail(toUser,subject,sb.toString(),path);
    }
}
