package com.live.order.service;

import com.live.order.domain.Order;

/**
* <pre>
* Service interface for Order Service operations, handles two operations. <ul>
*     <li>placeOrderRequest</li>
*     <li>cancelOrderRequest</li>
* </ul>
* </pre>
*
*
* @see OrderServiceImpl
*
*/
public interface OrderService {

    String placeOrder(Order order);

    boolean cancelOrder(String orderRef);
}
