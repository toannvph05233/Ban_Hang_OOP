package service;

import model.User;

import model.enumModel.Role;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private static Map<String, User> users = new HashMap<>();

    public UserService() {
        User user = new User(1, "admin", "1", "1", BCrypt.hashpw("admin", BCrypt.gensalt()), "1", "1", "1");
        user.setRole(Role.ROLE_ADMIN);
        users.put("admin", user);
    }

    public void register(Scanner scanner) {
        String username;
        while (true) {
            System.out.print("Nhập tên người dùng: ");
            username = scanner.nextLine();
            if (users.get(username) == null) {
                break;
            } else {
                System.out.println("Username đã tồn tại");
            }
        }
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        System.out.print("Nhập tên: ");
        String firstName = scanner.nextLine();
        System.out.print("Nhập họ: ");
        String lastName = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = scanner.nextLine();

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        List<User> users1 = new ArrayList<>(users.values());
        int id;
        if (users1.size() != 0) {
            id = users1.get(users1.size() - 1).getUserId() + 1;
        } else {
            id = 1;
        }
        // Tạo đối tượng User với thông tin đã nhập
        User newUser = new User(id, username, firstName, lastName, hashedPassword, email, phoneNumber, address);

        users.put(username, newUser);
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
            System.out.println(user);
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
                System.out.println(user);
            }
        }
    }

    public void blockUser(String username) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.setStatus(false);
            System.out.println("Tài khoản " + username + " đã bị khóa.");
        } else {
            System.out.println("Không tìm thấy người dùng có tên là " + username + ".");
        }
    }

    public void unblockUser(String username) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            user.setStatus(true);
            System.out.println("Tài khoản " + username + " đã được mở khóa.");
        } else {
            System.out.println("Không tìm thấy người dùng có tên là " + username + ".");
        }
    }
}
