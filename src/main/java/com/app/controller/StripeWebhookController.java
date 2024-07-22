package com.app.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RestController;

// import com.app.dto.order.OrderResponse;
// import com.app.persistence.entity.OrderStatus;
// import com.app.service.IOrderService;
// import com.stripe.exception.StripeException;
// import com.stripe.model.Event;
// import com.stripe.model.EventDataObjectDeserializer;
// import com.stripe.model.StripeObject;
// import com.stripe.model.checkout.Session;
// import com.stripe.net.Webhook;

// @RestController("api/v1/stripe/webhook")
public class StripeWebhookController {

    // @Autowired
    // private IOrderService orderService;

    // @Value("${stripe.webhook.secret}")
    // private String endpointSecret;

    // @PostMapping
    // public String handle(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
    //     if (endpointSecret == null) {
    //         return "";
    //     }

    //     Event event;

    //     try {
    //         event = Webhook.constructEvent(
    //                 payload, sigHeader, endpointSecret);
    //     } catch (StripeException e) {
    //         return "";
    //     }

    //     if ("checkout.session.completed".equals(event.getType())) {
    //         EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
    //         StripeObject stripeObject = null;
    //         if (dataObjectDeserializer.getObject().isPresent()) {
    //             stripeObject = dataObjectDeserializer.getObject().get();
    //         }

    //         if (stripeObject instanceof Session) {
    //             Session session = (Session) stripeObject;
    //             String orderId = session.getMetadata().get("orderId");
    //             updateOrderStatusToPaid(orderId);
    //         }
    //     }
    //     return "";
    // }

    // private void updateOrderStatusToPaid(String orderIdStr) {
    //     Long orderId = Long.parseLong(orderIdStr);
    //     OrderResponse currentOrder = orderService.findById(orderId);
    //     if (currentOrder != null) {
    //         orderService.updateOrderStatus(orderId, OrderStatus.COMPLETED);
    //     } else {
    //         throw new RuntimeException("Order not found");
    //     }

    // }

}
