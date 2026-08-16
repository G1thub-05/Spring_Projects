package in.digeshwar;

import org.springframework.core.annotation.Order;

public class ShoppingCart {

    public ShoppingCart(Ipayment upayment, Ipayment dpayment, Ipayment cpayment) {
        upayment.pay(500.568);
        dpayment.pay(900.877);
        cpayment.pay(200.566);
    }
    public static void PlaceOrder() {
        System.out.println("Order Placed Successfully");
    }
}
