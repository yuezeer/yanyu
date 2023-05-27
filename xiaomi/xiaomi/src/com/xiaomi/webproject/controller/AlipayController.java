package com.xiaomi.webproject.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xiaomi.webproject.bean.AlipayConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 陈靖文
 * @version 1.0
 */
@WebServlet("/test")
public class AlipayController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String money = req.getParameter("money");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //初始化
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
                AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                AlipayConfig.format,
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //填充业务参数
        //必填
        //商户订单号，需保证在商户端不重复
        String out_trade_no = id;
        //销售产品码，与支付宝签约的产品码名称。目前仅支持FAST_INSTANT_TRADE_PAY
        String product_code = "FAST_INSTANT_TRADE_PAY";
        //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。
        String total_amount = money;
        //订单标题
        String subject = "订单信息";

        //选填
        //商品描述，可空
        String body = "商品描述";
        alipayRequest.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"product_code\":\"" + product_code + "\"," +
                "\"total_amount\":\"" + total_amount + "\"," +
                "\"subject\":\"" + subject + "\"," +
                "\"body\":\"" + body + "\"}" );
        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();//调用SDK生成表单

        } catch (AlipayApiException e) {
            e.printStackTrace();
            resp.getWriter().write("捕获异常出错");
            resp.getWriter().flush();
            resp.getWriter().close();
        }
        resp.setContentType("text/html;charset=" + AlipayConfig.charset);
        resp.getWriter().write(form);//直接将完整的表单html输出到页面
        resp.getWriter().flush();
        resp.getWriter().close();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
