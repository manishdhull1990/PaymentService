package com.example.paymentservice.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RazorPaymentGateway implements PaymentGateway{
    private RazorpayClient razorpay;

    public RazorPaymentGateway(RazorpayClient razorpay){
        this.razorpay=razorpay;
    }
    @Override
    public String createPaymentLink(Long orderId) {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        //paymentLinkRequest.put("accept_partial",true);
        //paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1716401454);
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Payment for order id:"+orderId.toString());
        JSONObject customer = new JSONObject();
        customer.put("name","+918007161133");
        customer.put("contact","Manish Dhull");
        customer.put("email","manishdhull1990@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com/academy/mentee-dashboard/todos/");
        paymentLinkRequest.put("callback_method","get");
        PaymentLink payment=null;
        try{
            payment = razorpay.paymentLink.create(paymentLinkRequest);
        }catch (RazorpayException e){
            throw new RuntimeException(e);
        }
        return payment.toString();
    }
}
