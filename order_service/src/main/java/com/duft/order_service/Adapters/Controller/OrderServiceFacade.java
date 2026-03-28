package com.duft.order_service.Adapters.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.duft.order_service.Adapters.WebDTO.OrderItemRequest;
import com.duft.order_service.Adapters.WebDTO.OrderItemResponseDTO;
import com.duft.order_service.Adapters.WebDTO.OrderRequestDTO;
import com.duft.order_service.Adapters.WebDTO.OrderResponseDTO;
import com.duft.order_service.domain.entities.Order;
import com.duft.order_service.domain.entities.OrderItems;
import com.duft.order_service.domain.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderServiceFacade {

     private OrderService orderService;
    private static final ObjectMapper mapper = new ObjectMapper();

    public OrderServiceFacade(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderResponseDTO addToCart(OrderRequestDTO orderRequest){
        //TODO: first we will add this in order & orderItem table after calculating prices etc then pass call to payment service
        // Order order = new Order(null, orderRequest.getCustomer_id(), false, 0, new Date().toString());
            Map<Integer,Integer> priceList = new HashMap<Integer,Integer>();
            Integer dummyPrice = 5; //need to overide to update this with actual price fetch from product service
            Integer totalPrice = 0;
            for(OrderItemRequest orderItemRequest : orderRequest.getOrderItems()){
                priceList.put(orderItemRequest.getProduct_id(), dummyPrice * orderItemRequest.getQuantity());
                totalPrice += dummyPrice * orderItemRequest.getQuantity();
            }
           Order addOrder = orderService.addOrder(new Order(null, orderRequest.getCustomerId(), false, totalPrice, new Date().toString()));
           List<OrderItemResponseDTO> orderItemList = orderRequest.getOrderItems().stream()
                .map(orderItemRequest -> {
                    OrderItems orderItems = orderService.addOrderItems(new OrderItems(null, addOrder.getOrderId(), orderItemRequest.getProduct_id(), orderItemRequest.getQuantity(), priceList.get(orderItemRequest.getProduct_id())));
                    return new OrderItemResponseDTO(orderItems.getOrderItemId(), orderItems.getOrderId(), orderItems.getProductId(), orderItems.getQuantity(), orderItems.getPrice());
                })
                .collect(Collectors.toList());
    //TODO: create a sync call which will fetch prices for this orders and update it in db
      return  new OrderResponseDTO(addOrder.getOrderId(), addOrder.getCustomerId(), addOrder.getStatus(), addOrder.getTotal(), addOrder.getCreated_date(), orderItemList);
    }

    public List<OrderResponseDTO> getOrderList(Integer customer_id){
        List<Order> orderList = orderService.getOrderList(customer_id);
        List<OrderItems> orderItemsList = orderService.getOrderItemsList(customer_id);
        List<OrderResponseDTO> finalOrderList = new ArrayList<>();
        for(Order order : orderList){
            List<OrderItemResponseDTO> finalOrderItemList = new ArrayList<>();
            for(OrderItems orderItems : orderItemsList){
                if(order.getOrderId() == orderItems.getOrderId()){
                    finalOrderItemList.add(new OrderItemResponseDTO(orderItems.getOrderItemId(),order.getOrderId(),orderItems.getProductId(),orderItems.getQuantity(),orderItems.getPrice()));
                }
            }
            finalOrderList.add(new OrderResponseDTO(order.getOrderId(),order.getCustomerId(),order.getStatus(),order.getTotal(),order.getCreated_date(),finalOrderItemList));
        }
        return finalOrderList;
    }

    public OrderResponseDTO getOrderDetails(Integer orderId){
        Order order = orderService.getOrderDetails(orderId);
        List<OrderItems> orderItemsList = orderService.getOrderItemsListByOrderID(orderId);
        List<OrderItemResponseDTO> finalOrderItemList = new ArrayList<>();
        for(OrderItems orderItems : orderItemsList){
            finalOrderItemList.add(new OrderItemResponseDTO(orderItems.getOrderItemId(),orderItems.getOrderId(),orderItems.getProductId(),orderItems.getQuantity(),orderItems.getPrice()));
        }
        return new OrderResponseDTO(order.getOrderId(),order.getCustomerId(),order.getStatus(),order.getTotal(),order.getCreated_date(),finalOrderItemList);
    }

    public OrderResponseDTO getOrderStatus(Integer orderId){
        Order order = orderService.getOrderDetails(orderId);
        return new OrderResponseDTO(order.getOrderId(),order.getCustomerId(),order.getStatus(),order.getTotal(),order.getCreated_date(),null);
    }

    public Boolean updateOrderStatus(Integer orderId, Boolean status){
        orderService.updateOrderStatus(orderId, status);
        return true;
    }

    public OrderItemResponseDTO getOrderItemResponse(int orderId) {
        return mapper.convertValue(orderService.getOrderItem(orderId), OrderItemResponseDTO.class);
    }
}
