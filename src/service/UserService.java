package service;

import model.User;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private static Map<String, User> users = new HashMap<>();

    public UserService() {
        users.put("admin", new User("admin", BCrypt.hashpw("admin", BCrypt.gensalt()), "accept", "admin"));
    }

    public void register(Scanner scanner) {
        System.out.print("Nhập tên người dùng: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        users.put(username, new User(username, password, "user", "accept"));
        System.out.println("Đăng ký thành công.");
    }

    public User login(Scanner scanner) {
        System.out.print("Nhập tên người dùng: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        if (users.containsKey(username)) {
            User user = users.get(username);
            // Kiểm tra mật khẩu đã mã hóa với mật khẩu nhập vào
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public void displayUsers() {
        System.out.println("Danh sách người dùng:");
        for (User user : users.values()) {
            System.out.println("Tên người dùng: " + user.getUsername() + ", Vai trò: " + user.getRole() + ", Trạng thái: " + user.getStatus());
        }
    }

    public void searchUserByName(String name) {
        List<User> foundUsers = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getUsername().contains(name)) {
                foundUsers.add(user);
            }
        }
        if (foundUsers.isEmpty()) {
            System.out.println("Không tìm thấy người dùng nào có tên chứa '" + name + "'.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (User user : foundUsers) {
                System.out.println("Tên người dùng: " + user.getUsername() + ", Vai trò: " + user.getRole() + ", Trạng thái: " + user.getStatus());
            }
        }
    }

    public void blockUser(String username) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.setStatus("blocked");
            System.out.println("Tài khoản " + username + " đã bị khóa.");
        } else {
            System.out.println("Không tìm thấy người dùng có tên là " + username + ".");
        }
    }

    public void unblockUser(String username) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.setStatus("accept");
            System.out.println("Tài khoản " + username + " đã được mở khóa.");
        } else {
            System.out.println("Không tìm thấy người dùng có tên là " + username + ".");
        }
    }
}
