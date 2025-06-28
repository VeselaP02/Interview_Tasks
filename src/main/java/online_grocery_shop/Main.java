package online_grocery_shop;

import online_grocery_shop.entities.Order;
import online_grocery_shop.entities.Product;
import online_grocery_shop.services.InventoryService;
import online_grocery_shop.services.OrderService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryService inventoryService = new InventoryService();
    private static final OrderService orderService = new OrderService(inventoryService);

    public static void main(String[] args) {
        while (true){
            System.out.println("\n1. Add Product\n2. List Products\n3. Update Product\n4. Delete Product\n5. Place Order\n6. Exit");
            System.out.print("Choose: ");
            switch (scanner.nextInt()) {
                case 1 -> addProduct();
                case 2 -> listProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> placeOrder();
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid option.");
        }
    }


}


    private static void addProduct() {
        System.out.print("Name: "); String name = scanner.next();
        System.out.print("Price: "); BigDecimal price = scanner.nextBigDecimal();
        System.out.print("Quantity: "); int qty = scanner.nextInt();
        System.out.print("X coord: "); int x = scanner.nextInt();
        System.out.print("Y coord: "); int y = scanner.nextInt();

       inventoryService.addProduct(new Product(name,price,qty,x,y));
    }

    private static void listProducts() {
        for (Product p: inventoryService.listOfProducts()) {
            System.out.println(p.toString());
        }
    }

    private static void updateProduct() {
        System.out.print("Product name: ");
        String name = scanner.next();
        if (!inventoryService.hasProduct(name)) {
            System.out.println("Not found.");
            return;
        }
        System.out.print("New price: "); BigDecimal price = scanner.nextBigDecimal();
        System.out.print("New quantity: "); int qty = scanner.nextInt();
        System.out.print("New X: "); int x = scanner.nextInt();
        System.out.print("New Y: "); int y = scanner.nextInt();

        inventoryService.updateProduct(name, new Product(name, price, qty, x, y));
    }

    private static void deleteProduct() {
        System.out.print("Product name: ");
        String name = scanner.next();
        inventoryService.removeProduct(name);
    }

    private static void placeOrder() {
        Map<String, Integer> items = new HashMap<>();
        while (true) {
            System.out.print("Product name (or 'done'): ");
            String name = scanner.next();
            if (name.equalsIgnoreCase("done")) break;
            System.out.print("Quantity: ");
            int qty = scanner.nextInt();
            items.put(name, qty);
        }
        orderService.processOrder(new Order(items));
    }

}
