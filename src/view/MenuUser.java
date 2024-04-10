package view;

import service.CartService;
import service.CategoryService;
import service.ProductService;
import service.UserService;

import java.util.Scanner;

public class MenuUser {
    public static void displayMainMenu(ProductService productService, CategoryService categoryService, CartService cartService) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU USER =====");
            System.out.println("1. Tìm kiếm sản phẩm");
            System.out.println("2. Hiển thị sản phẩm nổi bật");
            System.out.println("3. Hiển thị sản phẩm theo danh mục");
            System.out.println("4. Danh sách sản phẩm");
            System.out.println("5. Hiển thị sản phẩm theo giá");
            System.out.println("6. Thêm vào giỏ hàng");
            System.out.println("7. Quản lý giỏ hàng");
            System.out.println("8. Thoát");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    productService.searchProductByName(scanner);
                    break;
                case 2:
                    productService.displayHotProducts();
                    break;
                case 3:
                    productService.displayProductsByCategory(categoryService);
                    break;
                case 4:
                    productService.displayProducts();
                    break;
                case 5:
                    displaySortProductMenu(scanner, productService);
                    break;
                case 6:
                    productService.addToCart(scanner, cartService);
                    break;
                case 7:
                    CartMenu.displayCartMenu(scanner, cartService);
                case 8:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
        scanner.close();
    }

    private static void displaySortProductMenu(Scanner scanner, ProductService productService) {
        System.out.println("===== MENU SẮP XẾP SẢN PHẨM =====");
        System.out.println("1. Sắp xếp theo giá tăng dần");
        System.out.println("2. Sắp xếp theo giá giảm dần");
        System.out.print("Nhập lựa chọn của bạn: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                productService.displayProductsSortedByPrice(true);
                break;
            case 2:
                productService.displayProductsSortedByPrice(false);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
        }
    }
}


