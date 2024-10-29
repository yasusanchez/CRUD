import java.sql.*;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conexión exitosa a la base de datos.");

            // Llama a cada operación CRUD
            insertarEmpleado(conexion, "Carlos", "Gerente", 80000);
            consultarEmpleados(conexion);
            actualizarEmpleado(conexion, 1, "Carlos", "Director", 90000);
            eliminarEmpleado(conexion, 1);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la conexión.");
        }
    }

    // Metodo para insertar un empleado
    private static void insertarEmpleado(Connection conexion, String nombre, String cargo, double salario)
            throws SQLException {
        String sql = "INSERT INTO empleados (nombre, cargo, salario) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, cargo);
            pstmt.setDouble(3, salario);
            pstmt.executeUpdate();
            System.out.println("Empleado insertado correctamente!");
        }
    }

    // Metodo para consultar empleados
    private static void consultarEmpleados(Connection conexion) throws SQLException {
        String sql = "SELECT * FROM empleados";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Nombre: %s, Cargo: %s, Salario: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("cargo"),
                        rs.getDouble("salario"));
            }
        }
    }

    // Metodo para actualizar un empleado
    private static void actualizarEmpleado(Connection conexion, int id, String nombre, String cargo, double salario)
            throws SQLException {
        String sql = "UPDATE empleados SET nombre = ?, cargo = ?, salario = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, cargo);
            pstmt.setDouble(3, salario);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Empleado actualizado correctamente!");
        }
    }

    // Metodo para eliminar un empleado
    private static void eliminarEmpleado(Connection conexion, int id) throws SQLException {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Empleado eliminado correctamente!");
        }
    }
}