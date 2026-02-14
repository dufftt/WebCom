package com.duft.payment_service.Controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@RestController
public class PaymentController {

    @Value("${razor.key.id}")
    private String KEY_ID;
    @Value("${razor.key.secret}")
    private String KEY_SECRET;

    @PostMapping("/create-order")
    public String createOrder(@RequestParam("amount") int amount) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(KEY_ID, KEY_SECRET);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100);
        orderRequest.put("currency", "INR");
        Order order = razorpay.orders.create(orderRequest);
        return order.toString();
    }

    @PostMapping("/payment-callback")
    public RedirectView paymentCallback(
            @RequestParam("razorpay_order_id") String razorpayOrderId,
            @RequestParam("razorpay_payment_id") String razorpayPaymentId,
            @RequestParam("razorpay_signature") String razorpaySignature) throws RazorpayException {
        try {
            // Verify the payment signature here
            String signature = razorpayOrderId + "|" + razorpayPaymentId;
            boolean isValid = Utils.verifySignature(signature, razorpaySignature, KEY_SECRET);

            if (isValid) {
                // Payment successful
                RedirectView redirectView = new RedirectView("/success.html?orderId=" + razorpayOrderId);
                return redirectView;
            } else {
                // Payment failed
                return new RedirectView("/failure.html"); // Create failure.html if needed
            }
        } catch (RazorpayException e) {
            System.err.println("Razorpay Exception during callback: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("General Exception during callback: " + e.getMessage());
            throw new RazorpayException("General exception during callback");
        }
    }

    @GetMapping("/get-key")
    public Map<String,String> getKey() {
        Map m = new HashMap<String,String>();
        m.put("key",KEY_ID);
        m.put("secret", KEY_SECRET);
        return m;
    }

}
