
import service.CategoryService;
import service.ProductService;
import service.UserService;
import view.MenuLogin;

import java.util.Scanner;


public class Main {
    private static UserService userService = new UserService();
    private static CategoryService categoryService = new CategoryService();
    private static ProductService productService = new ProductService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MenuLogin.userManagementMenu(scanner,userService, categoryService, productService);

    }
}
