package com.xiaomi.webproject.bean;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000122607203";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCjcJ0JicGFNnoWqgr2bzL46jUg9WaStGm5Op/1T4qoSjvhHtVDwf8zF0ZGVyqp+hzFnWD7kv1Sbknv4W/9qLfqXqnJh4Cve3I7tap8+CnPIA++AORRLGEGCrAEjMkQxu+cX3iRT7D7SwwhXQFmhrR93d0B1ZKFdCxyGyabP3sh3ayenhCIgA3sZEO56bt3aW3C+3Wx+OdADf0Ug/8KL/kUgvCidpHKxqtg8nCtnbhUqnYZY/4Sob2lVMBB/tkeIgO8p2So7S4Fyk35GCGirsIvjiQGhbmIB89hulGkDqtNQAcwR/TWcow0L+GUp/GqifzoJp2l/adAWuWb909n/6KzAgMBAAECggEARYTNBxWVPvhhDereJtw9rirQ1mgLbTNYFPWevFVNATAyMBtRUzTdO/oWZi0FdK9yNYwviFKJddp7kJEvdZGiSQpi5dnBHuM/P66SCf30qqlUiNkQRIa/7jX1kLnhfEVh/f2N5yEigoD1c+t/gQCjHTaVnwYdAK7diNuLnFVwJoLXk2CJQwC29kCdZhYN97KYgEiyRYzDys0HkWwZMgdInh1QqAGVvT9tV+88qQkgFotHML+gWpAgdFmM6ptIasGX9hW3DZ7dJlU7K4P/yitKMlTxni0HQ6fdv5xp8COsxcAOQeaUQspqfhQ3JN0GUvgN0aMQOQK2vDF1EdCeFdPgYQKBgQDvXM/a9C+smAyUpp71LS8tR2cUFUeJ3AOMzigX1EA46gHkDW2RSEWH/71qc7J1R5W/w47SS5gJVkWCQ/ZWoxm/sI6MxAkPDmbmB2ohmgxJRkg+JACaEEitU81hxCm5b8nm0f2f4l6y22wQrHWssPJLsaKEfYqzo5MsgSs5fnVbFwKBgQCuzNfT6Fft654R1B1MGKGereTz1HbMXlblsPblpfidslQ+rORJ4OaAI6KrkF/rS92FjVlLINkwBmGNOfHajN3ZhXN+Hz52IBrE1nej2RKLcnIzJQ7Ta//rSeR5VBsxVBrg++ii0TE5ebDGy5Q1xGE1VHuh4bV6uwK02abyxaIGxQKBgQDl/ZzuunBQe2yjLJJR7E+HRzNhFa0+Znu2wKO/eb8XsKmOceSUkwfUTEO5VF/l1pmpv4Ksx9vXJOD28MxypJbqHABsezRlFiaC4h71GGqzwWdK5hb58amTeZE/XlflCOfL9HupM75gRvtH7h3Hh+HYH1+TxHkQcJzKR3a78KlckwKBgQCmp5BCbeufX5vGHbiM+5LjqaF2wyM8d9lbhlvu2U1udD4Tv1yr/ytkEhWegrigqiUalogCs9++BOdu8Ubf+pvtLmnNXlZ74uICtwM8CNLAdq3giz1WX0qQ9lNJReHjJu+1fxbuXIoU5SugbSnyFTgk01ZIIdyXlcLfz/92yOxQjQKBgBpJRx9KQgH2ybYeUevVV9QJC6wZYc0P8gfuuEVWFQ+H7qNGbYIAPhDB0oS1ZG98M6+M208M8RpYvJF2tV6FA8olmMhtboQR8oJCQKZPuF0Q8ZUD1jbS81WhATYTpJGzUEJsQqX4Biz0j+c2XkbYreCSHAtNiPZV5d9p1RGumeFu";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArwkTLA1C6i2zd8f6NhCdtYiBNQ3kGU78MedwY/0A5/ixde8+q8gLbe2EtlOWB359OF0KzhF1ba4O0d/Wf6XU8S0sSsespwub7bMnUjzxsa9noiz/fxH4ybdAP8pk4bZDm9Jqi4fOBe7ezFwh1FFZziCI7EmVUux5wm79smV/60cLE4VrpQKvk5tOAJKbwiuWagGZ68TaTwvbmQZ1k4b5GXfm2r0XeTD66zDRsWVuTpIS+qsKJ6BxfKPCL6Nh/i8QxM+///z/5DAfdGyMqr1uERNZQOQOc66UKdA/28A8fQjfE28ZfgrMvzSbykR+obc1PUA6vMqT5xA/keLxcNc9ZwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/xiaomi/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/xiaomi/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 返回格式
	public static String format = "json";
//	// 支付宝网关
//	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
//    public static void logResult(String sWord) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
//            writer.write(sWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}

