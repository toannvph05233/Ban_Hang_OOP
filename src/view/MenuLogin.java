package view;

import model.User;
import service.CategoryService;
import service.ProductService;
import service.UserService;

import java.util.Scanner;

public class MenuLogin {
    public static void userManagementMenu(Scanner scanner, UserService userService, CategoryService categoryService, ProductService productService) {
        while (true) {
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Thoát");

            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    userService.register(scanner);
                    break;
                case 2:
                    User user = userService.login(scanner);
                    if (user != null) {
                        if (user.getRole().equals("admin")) {
                            MenuAdmin.menu(scanner,userService,categoryService,productService);
                        } else {
                            System.out.println("Chào user");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Kết thúc chương trình.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
