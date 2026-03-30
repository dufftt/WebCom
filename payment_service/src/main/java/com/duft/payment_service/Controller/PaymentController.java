package com.duft.payment_service.Controller;

import java.util.Map;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.duft.payment_service.Services.PaymentService;

import com.razorpay.RazorpayException;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam("amount") int amount) throws RazorpayException {
        return paymentService.createOrder(amount);
    }

    @PostMapping("/payment-callback")
    public RedirectView paymentCallback(
            @RequestParam("razorpay_order_id") String razorpayOrderId,
            @RequestParam("razorpay_payment_id") String razorpayPaymentId,
            @RequestParam("razorpay_signature") String razorpaySignature) throws RazorpayException {
       return paymentService.paymentCallback(razorpayOrderId, razorpayPaymentId, razorpaySignature);
    }

    @GetMapping("/get-key")
    public Map<String,String> getKey() {
       return paymentService.getKey();
    }

}
