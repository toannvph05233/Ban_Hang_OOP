package presentation;

import business.entity.User;
import business.entity.enumModel.Role;
import business.service.*;

import java.util.Scanner;

public class MenuLogin {
    public static void userManagementMenu(OrderService orderService, Scanner scanner, UserService userService, CategoryService categoryService, ProductService productService, CartService cartService) {
        String cyan = "\u001B[36m";
        String reset = "\u001B[0m";

            while (true) {
                System.out.println(cyan + "-------------------------------------------------------------------" + reset);
            System.out.println("1. Đăng ký");
            System.out.println("2. Đăng nhập");
            System.out.println("3. Thoát");

            System.out.print(cyan + "Chọn chức năng: " + reset);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    userService.register(scanner);
                    break;
                case 2:
                    User user = userService.login(scanner);
                    if (user != null) {
                        userService.setCurrentUser(user);
                        if (user.getRole() == Role.ROLE_ADMIN) {
                            MenuAdmin.menu(orderService,scanner, userService, categoryService, productService);
                        } else {
                            MenuUser.displayMainMenu(orderService,user, userService, productService, categoryService, cartService);
                        }
                    }else {
                        System.out.println("Sai Username or Password");
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
