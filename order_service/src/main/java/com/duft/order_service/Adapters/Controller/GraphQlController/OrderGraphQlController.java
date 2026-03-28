package com.duft.order_service.Adapters.Controller.GraphQlController;

import java.util.List;

import org.springframework.graphql.data.federation.EntityMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import com.duft.order_service.Adapters.Controller.OrderServiceFacade;
import com.duft.order_service.Adapters.WebDTO.OrderItemResponseDTO;
import com.duft.order_service.Adapters.WebDTO.OrderRequestDTO;
import com.duft.order_service.Adapters.WebDTO.OrderResponseDTO;

public class OrderGraphQlController {

     private OrderServiceFacade orderServiceFacade;


    public OrderGraphQlController(OrderServiceFacade orderServiceFacade) {
        this.orderServiceFacade = orderServiceFacade;
    }

    @MutationMapping
    public OrderResponseDTO addToCart(@Argument OrderRequestDTO orderRequest){
        OrderResponseDTO response = orderServiceFacade.addToCart(orderRequest);
      return  response;
    }

    @QueryMapping
    public List<OrderResponseDTO> getOrderList(@Argument Integer customer_id){
        List<OrderResponseDTO> orderList = orderServiceFacade.getOrderList(customer_id);
        return orderList;
    }
    @QueryMapping
    public OrderResponseDTO getOrderDetails(@Argument Integer orderId){
        OrderResponseDTO response = orderServiceFacade.getOrderDetails(orderId);
        return response;
    }
    @QueryMapping
    public OrderResponseDTO getOrderStatus(@Argument Integer orderId){
        OrderResponseDTO response = orderServiceFacade.getOrderStatus(orderId);
        return response;
    }
    @MutationMapping
    public Boolean updateOrderStatus(@Argument Integer orderId, @Argument Boolean status){
        return orderServiceFacade.updateOrderStatus(orderId, status);
    }
    @EntityMapping
    public OrderResponseDTO orderResponseDTO(int OrderId){
        return orderServiceFacade.getOrderDetails(OrderId);
    }
    @EntityMapping
    public OrderItemResponseDTO orderItemResponseDTO(int OrderId){
        return orderServiceFacade.getOrderItemResponse(OrderId);
    }

}
