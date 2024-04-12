package presentation;

import business.service.CategoryService;
import business.service.OrderService;
import business.service.ProductService;
import business.service.UserService;

import java.util.Scanner;

public class MenuAdmin {
    public static void menu(OrderService orderService, Scanner scanner, UserService userService, CategoryService categoryService, ProductService productService) {
        // Màu cho kẻ viền và menu
        String cyan = "\u001B[36m";
        String reset = "\u001B[0m";
        while (true) {
            System.out.println(cyan + "-------------------------------------------------------------------" + reset);
            System.out.println("--- Menu Admin ---");
            System.out.println("1. Quản lý Account");
            System.out.println("2. Quản lý Category");
            System.out.println("3. Quản lý Product");
            System.out.println("4. Quản lý Oder");
            System.out.println("5. Logout");

            System.out.print(cyan + "Chọn chức năng: " + reset);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    MenuManagerAccount.menu(scanner,  userService);
                    break;
                case 2:
                    MenuManagerCategory.menu(scanner, categoryService);
                    break;
                case 3:
                    MenuManagerProduct.menu(scanner, productService, categoryService);
                    break;
                case 4:
                    MenuOrder.displayOrderMenu(orderService,scanner);
                    break; // Quay lại menu chính
                case 5:
                    return; // Quay lại menu chính
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
