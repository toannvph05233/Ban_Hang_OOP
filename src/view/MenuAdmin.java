package view;

import service.CategoryService;
import service.ProductService;
import service.UserService;

import java.util.Scanner;

public class MenuAdmin {
    public static void menu(Scanner scanner, UserService userService, CategoryService categoryService, ProductService productService) {
        while (true) {
            System.out.println("--- Menu Admin ---");
            System.out.println("1. Quản lý Account");
            System.out.println("2. Quản lý Category");
            System.out.println("3. Quản lý Product");
            System.out.println("4. Logout");

            System.out.print("Chọn chức năng: ");
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
                    return; // Quay lại menu chính
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
