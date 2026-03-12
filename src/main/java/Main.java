import java.util.*;

public class Main {
    static List<Product> products = new ArrayList<>();
    static Set<Integer> ids = new HashSet<>();
    static Scanner input = new Scanner(System.in);

    public void create() {
        System.out.print("Enter product id: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter product name: ");
        String name = input.nextLine();

        System.out.print("Enter product price: ");
        double price = input.nextDouble();
        input.nextLine();

        System.out.print("Enter product quantity: ");
        int quantity = input.nextInt();
        input.nextLine();

        System.out.print("Enter product category: ");
        String category = input.nextLine();

        if (!ids.contains(id)) {
            ids.add(id);
            products.add(new Product(id, name, price, quantity, category));
        } else throw new InvalidProductException("Id already exists");
    }


    public void read() {
        if (products.isEmpty()) {
            System.out.println("No products exist");
            return;
        }
        System.out.printf("|  %-5s  |  %-20s  |  %-10s  |  %-10s  |  %-15s  |\n", "Id", "Name", "Price", "Quantity", "Category");
        products.forEach(p -> System.out.printf("|  %-5d  |  %-20s  |  %-10.1f  |  %-10d  |  %-15s  |\n",
                p.getId(), p.getName(), p.getPrice(), p.getQuantity(), p.getCategory()));
    }

    public void updateQuantityById() {
        System.out.print("Enter product id: ");
        int id = input.nextInt();
        input.nextLine();

        Optional<Product> optional = products.stream().filter(s -> s.getId() == id).findFirst();
        if (optional.isPresent()) {
            System.out.print("Enter new quantity: ");
            int quantity = input.nextInt();
            input.nextLine();
            optional.get().setQuantity(quantity);
        } else throw new InvalidProductException("Product not found");
    }

    public void delete() {
        products.removeIf(p -> {
            if (p.getQuantity() == 0) {
                ids.remove(p.getId());
                return true;
            }
            return false;
        });
        System.out.println("Product has been deleted");
    }

    public void menu() {
        System.out.println("========= PRODUCT MANAGEMENT SYSTEM =========");
        System.out.println("1. Them san pham moi");
        System.out.println("2. Hien thi danh sach san pham");
        System.out.println("3. Cap nhat so luong theo id");
        System.out.println("4. Xoa san pham da het hang");
        System.out.println("5. Thoat chuong trinh");
        System.out.println("=============================================");
    }

    public static void main(String[] args) {
        Main obj = new Main();

        do {
            obj.menu();
            System.out.print("Lua chon cua ban: ");
            int choice = input.nextInt();

            if (choice == 1) {
                obj.create();
            } else if (choice == 2) {
                obj.read();
            } else if (choice == 3) {
                obj.updateQuantityById();
            } else if (choice == 4) {
                obj.delete();
            } else if (choice == 5) {
                System.out.println("Chuong trinh ket thuc");
                break;
            } else System.out.println("Invalid choice");
        } while (true);
    }

}
