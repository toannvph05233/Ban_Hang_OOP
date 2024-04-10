package service;

import model.Product;
import model.Catalog;

import java.util.*;

public class ProductService {
    private static Map<Integer, Product> products = new HashMap<>();

    public void displayProducts() {
        System.out.println("Danh sách sản phẩm:");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    public void addProduct(Scanner scanner, CategoryService categoryService) {
        System.out.print("Nhập tên sản phẩm: ");
        String productName = scanner.nextLine();
        double unitPrice;
        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                unitPrice = Double.parseDouble(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("nhập sai định dạng");
            }
        }

        int stock;
        while (true) {
            try {
                System.out.print("Chọn số lượng của sản phẩm: ");
                stock = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.err.println("nhập sai định dạng");
            }
        }

        System.out.print("Nhập description sản phẩm: ");
        String description = scanner.nextLine();

        categoryService.displayCategories();
        System.out.print("Chọn ID danh mục của sản phẩm: ");
        String categoryId = scanner.nextLine();

        // Lấy danh mục từ ID
        Catalog category = categoryService.getCategories().get(categoryId);
        if (category != null) {
            int productId = getMaxId() + 1;
            // Tạo đối tượng Product với thông tin đã nhập
            Product product = new Product(productId, productName, categoryId, description, unitPrice, stock);

            // Thêm sản phẩm vào danh sách sản phẩm
            products.put(productId, product);

            System.out.println("Sản phẩm đã được thêm vào.");
        } else {
            System.out.println("Không tìm thấy danh mục.");
        }
    }

    public void editProduct(Scanner scanner, CategoryService categoryService) {
        System.out.print("Nhập ID của sản phẩm cần chỉnh sửa: ");
        String productId = scanner.nextLine();

        if (products.containsKey(productId)) {
            Product product = products.get(productId);

            System.out.print("Nhập tên mới của sản phẩm: ");
            String newName = scanner.nextLine();
            product.setProductName(newName);

            while (true) {
                System.out.print("Nhập ID danh mục mới của sản phẩm: ");
                categoryService.displayCategories();
                String newCategoryId = scanner.nextLine();
                Catalog category = categoryService.getCategories().get(newCategoryId);
                if (category != null) {
                    product.setCategoryId(newCategoryId);
                    break;
                } else {
                    System.out.println("Không tìm thấy danh mục.");
                }
            }

            System.out.print("Nhập mô tả mới của sản phẩm: ");
            String newDescription = scanner.nextLine();
            product.setDescription(newDescription);

            System.out.print("Nhập giá mới của sản phẩm: ");
            double newUnitPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character
            product.setUnitPrice(newUnitPrice);

            System.out.print("Nhập số lượng mới của sản phẩm: ");
            int newStock = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            product.setStock(newStock);

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
            if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                foundProducts.add(product);
            }
        }
        if (foundProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào có tên chứa '" + name + "'.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Product product : foundProducts) {
                System.out.println(product);
            }
        }
    }

    private Catalog findCategoryByName(List<Catalog> categories, String name) {
        for (Catalog catalog : categories) {
            if (catalog.getCatalogName().equalsIgnoreCase(name)) {
                return catalog;
            }
        }
        return null;
    }

    public int getMaxId() {
        int idMax = 0;
        for (Product p : products.values()) {
            if (idMax < p.getProductId()) {
                idMax = p.getProductId();
            }
        }
        return idMax;
    }

    public void displayProductsByCategory(CategoryService categoryService) {
        System.out.println("Danh sách sản phẩm theo danh mục:");
        for (Catalog category : categoryService.getCategories().values()) {
            System.out.println("Danh mục: " + category.getCatalogName());
            for (Product product : products.values()) {
                if (product.getCategoryId().equals(category.getCatalogId())) {
                    System.out.println(product);
                }
            }
            System.out.println();
        }
    }

    public void displayProductsSortedByPrice(boolean ascending) {
        List<Product> sortedProducts = new ArrayList<>(products.values());
        if (ascending) {
            sortedProducts.sort(Comparator.comparingDouble(Product::getUnitPrice));
        } else {
            sortedProducts.sort((p1, p2) -> Double.compare(p2.getUnitPrice(), p1.getUnitPrice()));
        }
        System.out.println("Danh sách sản phẩm được sắp xếp theo giá:");
        for (Product product : sortedProducts) {
            System.out.println(product);
        }
    }

    public void addToCart(Scanner scanner, CartService cartService) {
        System.out.print("Nhập ID của sản phẩm cần thêm vào giỏ hàng: ");
        int productId = Integer.parseInt(scanner.nextLine());
        if (products.containsKey(productId)) {
            Product product = products.get(productId);
            System.out.print("Nhập số lượng: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            cartService.addToCart(product, quantity);
            System.out.println("Đã thêm sản phẩm vào giỏ hàng.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có ID là " + productId);
        }
    }

    public void displayHotProducts() {
        System.out.println("Danh sách sản phẩm nổi bật:");
        for (Product product : products.values()) {
            if (product.isHotProduct()) {
                System.out.println(product);
            }
        }
    }


}

