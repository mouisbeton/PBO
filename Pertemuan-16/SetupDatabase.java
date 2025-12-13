import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SetupDatabase {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println("Creating database...");
            String sql = "CREATE DATABASE IF NOT EXISTS perpustakaan";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully!");

            System.out.println("Selecting database...");
            stmt.executeUpdate("USE perpustakaan");

            System.out.println("Creating table...");
            sql = "CREATE TABLE IF NOT EXISTS buku (" +
                  "id_buku INT NOT NULL AUTO_INCREMENT, " +
                  "judul VARCHAR(45) NOT NULL, " +
                  "pengarang VARCHAR(45) NOT NULL, " +
                  "PRIMARY KEY (id_buku))";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully!");

            System.out.println("Inserting data...");
            sql = "INSERT INTO buku (judul, pengarang) VALUES " +
                  "('Belajar Pemrograman Java dari Nol', 'Petani Kode'), " +
                  "('Pemrograman Java Menggunakan Linux', 'Petani Kode')";
            stmt.executeUpdate(sql);
            System.out.println("Data inserted successfully!");

            System.out.println("\nSetup complete! You can now run JavaCRUD.");

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
