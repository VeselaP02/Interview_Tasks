package online_grocery_shop.entities;

import java.util.List;
import java.util.Map;

public class Order {
    private Map<String,Integer> requestedProducts;

    public Order(Map<String, Integer> requestedProducts) {
        this.requestedProducts = requestedProducts;
    }

    public Map<String, Integer> getRequestedProducts() {
        return requestedProducts;
    }
}
