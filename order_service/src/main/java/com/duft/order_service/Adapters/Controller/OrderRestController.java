package com.duft.order_service.Adapters.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duft.order_service.Adapters.WebDTO.OrderItemRequest;
import com.duft.order_service.Adapters.WebDTO.OrderRequestDTO;
import com.duft.order_service.domain.entities.Order;
import com.duft.order_service.domain.entities.OrderItems;
import com.duft.order_service.domain.services.OrderService;

@RestController
@RequestMapping("/orderService")
public class OrderRestController {

    private OrderService orderService;


    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<Boolean> addToCart(@RequestBody OrderRequestDTO orderRequest){
        //TODO: first we will add this in order & orderItem table after calculating prices etc then pass call to payment service
        Order order = new Order(null, orderRequest.getCustomer_id(), false, 0, new Date().toString());
           Order addOrder = orderService.addOrder(order);
           List<OrderItems> orderItemList=new ArrayList<>();
        for(OrderItemRequest orderItemRequest: orderRequest.getOrderItems()){
            OrderItems orderItems = new OrderItems(null, addOrder.getOrderId(), orderItemRequest.getProduct_id(), orderItemRequest.getQuantity(), 0);
            orderItemList.add(orderService.addOrderItems(orderItems));
        }
    //TODO: create a sync call which will fetch prices for this orders and update it in db
   //TODO: return list of Order<OrderItems> back to user so that UI can show the price details
        ResponseEntity.ok(new ArrayList<>());
    return ResponseEntity.ok(true);
    }



}
