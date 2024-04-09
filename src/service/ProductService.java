package service;

import model.Product;
import model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductService {
    private static Map<String, Product> products = new HashMap<>();
    private static int productIdCounter = 1;

    public void displayProducts() {
        System.out.println("Danh sách sản phẩm:");
        for (Product product : products.values()) {
            System.out.println("ID: " + product.getId() + ", Tên: " + product.getName() + ", Giá: " + product.getPrice() + ", Danh mục: " + product.getCategory().getName());
        }
    }

    public void addProduct(Scanner scanner,  CategoryService categoryService) {
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Chọn ID danh mục của sản phẩm: ");
        categoryService.displayCategories();
        String idCategory = scanner.nextLine();
        Category category = categoryService.getCategories().get(idCategory);
        if (category != null) {
            String productId = String.valueOf(productIdCounter++);
            Product product = new Product(productId, name, price, category);
            products.put(productId, product);
            System.out.println("Sản phẩm đã được thêm vào.");
        } else {
            System.out.println("Không tìm thấy danh mục.");
        }
    }

    public void editProduct(Scanner scanner) {
        System.out.print("Nhập ID của sản phẩm cần chỉnh sửa: ");
        String productId = scanner.nextLine();
        if (products.containsKey(productId)) {
            Product product = products.get(productId);
            System.out.print("Nhập tên mới của sản phẩm: ");
            String newName = scanner.nextLine();
            System.out.print("Nhập giá mới của sản phẩm: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
            product.setName(newName);
            product.setPrice(newPrice);
            System.out.println("Thông tin sản phẩm đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);
        }
    }

    public void hideProductById(Scanner scanner) {
        System.out.print("Nhập ID của sản phẩm cần ẩn: ");
        String productId = scanner.nextLine();
        if (products.containsKey(productId)) {
            products.remove(productId);
            System.out.println("Sản phẩm đã được ẩn.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);
        }
    }

    public void hideProductsByIds(Scanner scanner) {
        System.out.print("Nhập danh sách ID của các sản phẩm cần ẩn (cách nhau bằng dấu phẩy): ");
        String[] productIds = scanner.nextLine().split(",");
        int count = 0;
        for (String productId : productIds) {
            if (products.containsKey(productId)) {
                products.remove(productId);
                count++;
            }
        }
        System.out.println(count + " sản phẩm đã được ẩn.");
    }

    public void searchProductByName(Scanner scanner) {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine();
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().contains(name)) {
                foundProducts.add(product);
            }
        }
        if (foundProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào có tên chứa '" + name + "'.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Product product : foundProducts) {
                System.out.println("ID: " + product.getId() + ", Tên: " + product.getName() + ", Giá: " + product.getPrice() + ", Danh mục: " + product.getCategory().getName());
            }
        }
    }

    private Category findCategoryByName(List<Category> categories, String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }
}

