import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRes = null;

        try {
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project",
                    "root",
                    ""
            );
            System.out.println("Genial, nos conectamos");

            // Para crear una declaracion
            myStmt = myConn.createStatement();

            // Ejecutar una consulta (ajustado para la tabla 'employees')
            String sql = "SELECT * FROM employees";
            myRes = myStmt.executeQuery(sql);

            // Procesa el resultado
            while (myRes.next()) {
                // Obtener datos de cada columna
                int id = myRes.getInt("id");
                String firstName = myRes.getString("first_name");
                String paSurname = myRes.getString("pa_surname");
                String maSurname = myRes.getString("ma_surname");
                String email = myRes.getString("email");
                double salary = myRes.getDouble("salary");

                // Imprime los datos
                System.out.println("ID: " + id);
                System.out.println("Nombre: " + firstName);
                System.out.println("Apellido paterno: " + paSurname);
                System.out.println("Apellido materno: " + maSurname);
                System.out.println("Email: " + email);
                System.out.println("Salario: " + salary);
                System.out.println("--------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo salió mal :(");
        } finally {
            // Cerrar recursos
            try {
                if (myRes != null) myRes.close();
                if (myStmt != null) myStmt.close();
                if (myConn != null) myConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
