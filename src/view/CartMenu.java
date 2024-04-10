package view;

import service.CartService;

import java.util.Scanner;

public class CartMenu {
    public static void displayCartMenu(Scanner scanner, CartService cartService) {
        int choice;
        do {
            System.out.println("\n===== CART MENU =====");
            System.out.println("1. Hiển thị danh sách sản phẩm trong giỏ hàng");
            System.out.println("2. Thay đổi số lượng đặt hàng");
            System.out.println("3. Xóa sản phẩm trong giỏ hàng");
            System.out.println("4. Đặt hàng");
            System.out.println("0. Quay lại menu chính");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (choice) {
                case 1:
                    cartService.displayCart();
                    break;
                case 2:
                    cartService.changeQuantity(scanner);
                    break;
                case 3:
                    cartService.removeFromCart(scanner);
                    break;
                case 4:
                    cartService.placeOrder();
                    break;
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }
}
