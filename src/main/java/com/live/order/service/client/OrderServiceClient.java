package com.live.order.service.client;

import java.util.logging.Logger;

import org.springframework.ws.client.core.WebServiceTemplate;

import com.live.order.domain.CancelOrderRequest;
import com.live.order.domain.CancelOrderResponse;
import com.live.order.domain.ObjectFactory;
import com.live.order.domain.Order;
import com.live.order.domain.PlaceOrderRequest;
import com.live.order.domain.PlaceOrderResponse;
import com.live.order.service.OrderService;

public class OrderServiceClient implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceClient.class.getName());
    private static final ObjectFactory WS_CLIENT_FACTORY = new     ObjectFactory();

    private  WebServiceTemplate webServiceTemplate;

    public OrderServiceClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public boolean cancelOrder(String orderRef) {
        logger.info("Preparing CancelOrderRequest.....");
        CancelOrderRequest request =   WS_CLIENT_FACTORY.createCancelOrderRequest();
        request.setRefNumber(orderRef);

        logger.info("Invoking Web service Operation[CancelOrder]....");
        CancelOrderResponse response = (CancelOrderResponse) webServiceTemplate.marshalSendAndReceive(request);

        logger.info("Has the order cancelled: " + response.isCancelled());

        return response.isCancelled();
    }

    public String placeOrder(Order order) {
        logger.info("Preparing PlaceOrderRequest.....");
                PlaceOrderRequest request = WS_CLIENT_FACTORY.createPlaceOrderRequest();
                request.setOrder(order);

        logger.info("Invoking Web service Operation[PlaceOrder]....");
                PlaceOrderResponse response = (PlaceOrderResponse) webServiceTemplate.marshalSendAndReceive(request);
        logger.info("Order reference:" + response.getRefNumber());
        return response.getRefNumber();
    }
}