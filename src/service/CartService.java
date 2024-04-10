package service;

import model.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CartService {
    private Map<Product, Integer> cart;

    public CartService() {
        this.cart = new HashMap<>();
    }

    public void addToCart(Product product, int quantity) {
        if (cart.containsKey(product)) {
            int currentQuantity = cart.get(product);
            cart.put(product, currentQuantity + quantity);
        } else {
            cart.put(product, quantity);
        }
        System.out.println("Đã thêm sản phẩm vào giỏ hàng.");
    }

    public void displayCart() {
        if (cart.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
        } else {
            System.out.println("Danh sách sản phẩm trong giỏ hàng:");
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(product.getProductName() + " - Số lượng: " + quantity);
            }
        }
    }

    public void changeQuantity(Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
            return;
        }
        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        int index = 1;
        Map<Integer, Product> productMap = new HashMap<>();
        for (Product product : cart.keySet()) {
            System.out.println(index + ". " + product.getProductName() + " - Số lượng: " + cart.get(product));
            productMap.put(index, product);
            index++;
        }
        System.out.print("Chọn số thứ tự của sản phẩm cần thay đổi số lượng: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice < 1 || choice > productMap.size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }
        Product selectedProduct = productMap.get(choice);
        System.out.print("Nhập số lượng mới: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        cart.put(selectedProduct, newQuantity);
        System.out.println("Số lượng của sản phẩm đã được cập nhật trong giỏ hàng.");
    }

    public void removeFromCart(Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
            return;
        }
        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        int index = 1;
        Map<Integer, Product> productMap = new HashMap<>();
        for (Product product : cart.keySet()) {
            System.out.println(index + ". " + product.getProductName() + " - Số lượng: " + cart.get(product));
            productMap.put(index, product);
            index++;
        }
        System.out.print("Chọn số thứ tự của sản phẩm cần xóa: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        if (choice < 1 || choice > productMap.size()) {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }
        Product selectedProduct = productMap.get(choice);
        cart.remove(selectedProduct);
        System.out.println("Sản phẩm đã được xóa khỏi giỏ hàng.");
    }

    public void placeOrder() {
        cart = new HashMap<>();
    }
}

