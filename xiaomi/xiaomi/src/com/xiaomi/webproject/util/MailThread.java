package com.xiaomi.webproject.util;
import com.xiaomi.webproject.bean.Admin;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class MailThread implements Runnable{

    private String from;//发送者邮箱
    private String to;//接受者邮箱
    private String host;//邮件服务器地址
    private String protocol;//邮件服务器协议
    private String password;//发送者邮箱密码

    private Admin user;

    public MailThread(Admin user) {
        this.user = user;
    }

    @Override
    public void run() {
        InputStream inputStream = MailThread.class.
                getClassLoader().
                getResourceAsStream("mail.properties");

        Properties p = new Properties();
        try {
            p.load(inputStream);
            from = p.getProperty("mail.from");
            host = p.getProperty("mail.host");
            protocol = p.getProperty("mail.transport.protocol");
            password  = p.getProperty("mail.password");

            to = user.getEmail();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //创建发送邮件会话
        Session session = Session.getInstance(p, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        //设置Debug 控制台查看发送邮件具体过程 默认是false
        session.setDebug(true);
        
        //创建Transport对象
        try {
            Transport transport = session.getTransport();
            //连接邮件服务器
            transport.connect(host,from,password);
            //创建MimeMessage对象
            MimeMessage mimeMessage = new MimeMessage(session);
            //设置标题
            mimeMessage.setSubject("邮件激活通知");
            //设置发送者
            mimeMessage.setFrom(new InternetAddress(from));
            //设置接收者
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, to);

            //生成激活码 随机生成4个数字的激活码
            String code = "";
            Random random = new Random();
            for(int i = 0; i < 4; i++){
                int c = random.nextInt(10);
                code += c;
            }
            //设置正文
            String content = "恭喜您，注册成功^_^,您的此次的激活码是" +
                    "<a href=http://127.0.0.1:8080/xiaomi/user?method=active" +
                    "&email="+user.getEmail()+"&code="+code+"&sendTime="+
                    System.currentTimeMillis()+">"+code+"" +
                    "</a>，请在3分钟内激活";

            //设置正文
            mimeMessage.setContent(content ,"text/html;charset=UTF-8");

            //发送邮件  getAllRecipients：邮件抄送者都可以接收到邮件
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

            //关闭连接
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
