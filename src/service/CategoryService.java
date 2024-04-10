package service;

import model.Catalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CategoryService {
    private static Map<String, Catalog> categories = new HashMap<>();
    private static int categoryIdCounter = 1; // Dùng để tự động tăng ID cho danh mục

    public void displayCategories() {
        System.out.println("Danh sách danh mục:");
        for (Catalog catalog : categories.values()) {
            System.out.println(catalog);
        }
    }

    public void addCategory(Scanner scanner) {
        System.out.print("Nhập tên danh mục mới: ");
        String name = scanner.nextLine();
        System.out.print("Nhập description danh mục: ");
        String description = scanner.nextLine();
        List<Catalog> catalogs = (List<Catalog> )categories.values();
        String categoryId = catalogs.get(catalogs.size()-1).getCatalogId() + 1;
        Catalog catalog = new Catalog(categoryId, name, description);
        categories.put(categoryId, catalog);
        System.out.println("Danh mục mới đã được thêm vào.");
    }

    public void editCategory(Scanner scanner) {
        System.out.print("Nhập ID của danh mục cần chỉnh sửa: ");
        String categoryId = scanner.nextLine();
        if (categories.containsKey(categoryId)) {
            System.out.print("Nhập tên mới của danh mục: ");
            String newName = scanner.nextLine();
            System.out.print("Nhập description danh mục: ");
            String description = scanner.nextLine();
            Catalog catalog = categories.get(categoryId);
            catalog.setCatalogName(newName);
            catalog.setDescription(description);
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
        List<Catalog> foundCategories = new ArrayList<>();
        for (Catalog catalog : categories.values()) {
            if (catalog.getCatalogName().contains(name)) {
                foundCategories.add(catalog);
            }
        }
        if (foundCategories.isEmpty()) {
            System.out.println("Không tìm thấy danh mục nào có tên chứa '" + name + "'.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Catalog catalog : foundCategories) {
                System.out.println(catalog);
            }
        }
    }

    public Map<String, Catalog> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Catalog> categories) {
        CategoryService.categories = categories;
    }
}
