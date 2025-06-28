package online_grocery_shop.services;

import online_grocery_shop.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class RoutePlanner {

    public static List<int[]> planRoute(List<Product> products) {
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{0, 0});
        for (Product p : products) {
            path.add(new int[]{p.getX(), p.getY()});
        }
        path.add(new int[]{0, 0});
        return path;
    }
}
