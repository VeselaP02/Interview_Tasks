package online_grocery_shop.services;

import online_grocery_shop.entities.Order;
import online_grocery_shop.entities.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderService {

     private final InventoryService inventoryService;


    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public boolean processOrder(Order order){
        List<Product> productsToPick = new ArrayList<>();

        boolean hasAll = true;

        for (var entry: order.getRequestedProducts().entrySet()) {
            String name = entry.getKey();
            int requested = entry.getValue();
            Product product = inventoryService.getProduct(name);

            if (product == null || product.getQuantity() < requested){
                System.out.println("Missing items: ");
                System.out.println("  "+ name + ": requested " + requested + ", available " +(product == null ? 0 :product.getQuantity()));

                hasAll = false;
            } else {
                productsToPick.add(product);
            }
        }

        if (!hasAll) return false;

        for (var entry: order.getRequestedProducts().entrySet()) {
            Product product = inventoryService.getProduct(entry.getKey());
            product.setQuantity(product.getQuantity() - entry.getValue());
        }

        List<int[]> path = RoutePlanner.planRoute(productsToPick);
        System.out.println("Order ready! Please collect it at the desk");
        System.out.print("Visited locations: ");
        for (int[] point : path) {
            System.out.print(Arrays.toString(point) + " ");
        }
        System.out.println();
        return true;
    }
}
