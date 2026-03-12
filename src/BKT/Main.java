package BKT;

import java.util.*;
import java.util.stream.*;

public class Main {

    static List<Product> products = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("===== PRODUCT MANAGEMENT SYSTEM =====");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát chương trình");
            System.out.println("=============================================");
            System.out.print("Lựa chọn của bạn: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    updateQuantity();
                    break;
                case 4:
                    deleteOutOfStock();
                    break;
                case 5:
                    System.out.println("Bạn đã chọn thoát");
                    return;
                default:
                    System.out.println("Lựa chọn của bạn không hợp lệ");
            }
        }
    }
// case1
    static void addProduct() {
        try {
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            boolean exists = products.stream()
                    .anyMatch(p -> p.getId() == id);
            if (exists) {
                throw new InvalidProductException("ID đã tồn tại");
            }
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            System.out.print("Quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine();
            System.out.print("Category: ");
            String category = sc.nextLine();
            Product p = new Product(id, name, price, quantity, category);
            products.add(p);
            System.out.println("Thêm thành công!");
        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage());

        }

    }
// case 2
    static void displayProducts() {
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
// case 3
    static void updateQuantity() {
        try {
            System.out.print("Nhập ID sản phẩm muốn cập nhật: ");
            int id = sc.nextInt();
            System.out.print("Số lượng mới: ");
            int newQty = sc.nextInt();
            Optional<Product> product = products.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst();
            if (product.isEmpty()) {
                throw new InvalidProductException("Không tìm thấy sản phẩm");
            }
            product.get().setQuantity(newQty);
            System.out.println("Cập nhật thành công");
        } catch (InvalidProductException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
// case 4
    static void deleteOutOfStock() {
        products.removeIf(p -> p.getQuantity() == 0);
        System.out.println("Đã xóa những sản phẩm hết hàng");
    }

}