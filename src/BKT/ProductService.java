package BKT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) throws InvalidProductException {
        boolean exists = products.stream()
                .anyMatch(p -> p.getId() == product.getId());
        if (exists) {
            throw new InvalidProductException("ID already exists!");
        }
        products.add(product);
    }

    public void displayProducts() {
        System.out.printf("%-5s %-15s %-10s %-10s %-15s\n",
                "ID", "Name", "Price", "Qty", "Category");
        products.forEach(p ->
                System.out.printf("%-5d %-15s %-10.2f %-10d %-15s\n",
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getQuantity(),
                        p.getCategory())
        );
    }

    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        if (product.isEmpty()) {
            throw new InvalidProductException("Product not found!");
        }
        product.get().setQuantity(newQuantity);
    }

    public void deleteOutOfStock() {
        products.removeIf(p -> p.getQuantity() == 0);
    }
}
