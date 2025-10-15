import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Delete All Books");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewBooks(); break;
                case 3: updateBook(); break;
                case 4: deleteBook(); break;
                case 5:deleteAllBooks();
                break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void addBook() {
        System.out.print("Title: "); String title = sc.nextLine();
        System.out.print("Author: "); String author = sc.nextLine();
        System.out.print("Quantity: "); int qty = sc.nextInt();
        sc.nextLine();

        try (Connection con = Database.getConnection()) {
            String sql = "INSERT INTO books(title, author, quantity) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setInt(3, qty);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Book added successfully!");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void viewBooks() {
        try (Connection con = Database.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            System.out.println("ID | Title | Author | Quantity");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("book_id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getInt("quantity")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void updateBook() {
        System.out.print("Enter Book ID to update: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("New Quantity: "); int qty = sc.nextInt(); sc.nextLine();

        try (Connection con = Database.getConnection()) {
            String sql = "UPDATE books SET quantity=? WHERE book_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, qty);
            pst.setInt(2, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Book updated successfully!");
            else System.out.println("Book ID not found.");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: "); int id = sc.nextInt(); sc.nextLine();

        try (Connection con = Database.getConnection()) {
            String sql = "DELETE FROM books WHERE book_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Book deleted successfully!");
            else System.out.println("Book ID not found.");
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public static void deleteAllBooks() {
    Connection con = Database.getConnection();
    try {
        String query = "DELETE FROM books";
        PreparedStatement ps = con.prepareStatement(query);
        int rows= ps.executeUpdate();
        System.out.println("All books deleted successfully!");
        ps.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
