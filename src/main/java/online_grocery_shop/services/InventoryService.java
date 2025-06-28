package online_grocery_shop.services;

import online_grocery_shop.entities.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private Map<String, Product> inventory = new HashMap<>();


    public void addProduct(Product product){
        inventory.put(product.getName(),product);
    }

    public void updateProduct(String name,Product updatedProduct){
        inventory.put(name,updatedProduct);
    }

    public void removeProduct(String name){
        inventory.remove(name);
    }

    public Product  getProduct(String name){
       return inventory.get(name);
    }

    public Collection<Product> listOfProducts(){
        return inventory.values();
    }

    public boolean hasProduct(String name){
       return inventory.containsKey(name);
    }
}
