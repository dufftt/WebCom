package com.duft.order_service.Adapters.Controller.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.duft.order_service.Adapters.Controller.OrderServiceFacade;
import com.duft.order_service.Adapters.WebDTO.OrderRequestDTO;
import com.duft.order_service.Adapters.WebDTO.OrderResponseDTO;


@RestController
@RequestMapping("/orderService")
public class OrderRestController {

    private OrderServiceFacade orderServiceFacade;


    public OrderRestController(OrderServiceFacade orderServiceFacade) {
        this.orderServiceFacade = orderServiceFacade;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<OrderResponseDTO> addToCart(@RequestBody OrderRequestDTO orderRequest){
        OrderResponseDTO response = orderServiceFacade.addToCart(orderRequest);
      return  ResponseEntity.ok(response);
    }

    @GetMapping("/getOrderList")
    public ResponseEntity<List<OrderResponseDTO>> getOrderList(@RequestParam Integer customer_id){
        List<OrderResponseDTO> orderList = orderServiceFacade.getOrderList(customer_id);
        return ResponseEntity.ok(orderList);
    }
    @GetMapping("/getOrderDetails")
    public ResponseEntity<OrderResponseDTO> getOrderDetails(@RequestParam Integer orderId){
        OrderResponseDTO response = orderServiceFacade.getOrderDetails(orderId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getOrderStatus")
    public ResponseEntity<OrderResponseDTO> getOrderStatus(@RequestParam Integer orderId){
        OrderResponseDTO response = orderServiceFacade.getOrderStatus(orderId);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/updateOrderStatus")
    public ResponseEntity<Boolean> updateOrderStatus(@RequestParam Integer orderId, @RequestParam Boolean status){
        return ResponseEntity.ok(orderServiceFacade.updateOrderStatus(orderId, status));
    }
}
