package service;

import model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CategoryService {
    private static Map<String, Category> categories = new HashMap<>();
    private static int categoryIdCounter = 1; // Dùng để tự động tăng ID cho danh mục

    public void displayCategories() {
        System.out.println("Danh sách danh mục:");
        for (Category category : categories.values()) {
            System.out.println("ID: " + category.getId() + ", Tên: " + category.getName());
        }
    }

    public void addCategory(Scanner scanner) {
        System.out.print("Nhập tên danh mục mới: ");
        String name = scanner.nextLine();
        String categoryId = String.valueOf(categoryIdCounter++);
        Category category = new Category(categoryId, name);
        categories.put(categoryId, category);
        System.out.println("Danh mục mới đã được thêm vào.");
    }

    public void editCategory(Scanner scanner) {
        System.out.print("Nhập ID của danh mục cần chỉnh sửa: ");
        String categoryId = scanner.nextLine();
        if (categories.containsKey(categoryId)) {
            System.out.print("Nhập tên mới của danh mục: ");
            String newName = scanner.nextLine();
            Category category = categories.get(categoryId);
            category.setName(newName);
            System.out.println("Thông tin danh mục đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy danh mục có ID là " + categoryId);
        }
    }

    public void hideCategoryById(Scanner scanner) {
        System.out.print("Nhập ID của danh mục cần ẩn: ");
        String categoryId = scanner.nextLine();
        if (categories.containsKey(categoryId)) {
            categories.remove(categoryId);
            System.out.println("Danh mục đã được ẩn.");
        } else {
            System.out.println("Không tìm thấy danh mục có ID là " + categoryId);
        }
    }

    public void hideCategoriesByIds(Scanner scanner) {
        System.out.print("Nhập danh sách ID của các danh mục cần ẩn (cách nhau bằng dấu phẩy): ");
        String[] categoryIds = scanner.nextLine().split(",");
        int count = 0;
        for (String categoryId : categoryIds) {
            if (categories.containsKey(categoryId)) {
                categories.remove(categoryId);
                count++;
            }
        }
        System.out.println(count + " danh mục đã được ẩn.");
    }

    public void searchCategoryByName(Scanner scanner) {
        System.out.print("Nhập tên danh mục cần tìm: ");
        String name = scanner.nextLine();
        List<Category> foundCategories = new ArrayList<>();
        for (Category category : categories.values()) {
            if (category.getName().contains(name)) {
                foundCategories.add(category);
            }
        }
        if (foundCategories.isEmpty()) {
            System.out.println("Không tìm thấy danh mục nào có tên chứa '" + name + "'.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Category category : foundCategories) {
                System.out.println("ID: " + category.getId() + ", Tên: " + category.getName());
            }
        }
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Category> categories) {
        CategoryService.categories = categories;
    }
}
