import java.sql.*;
import java.util.List;
import java.util.Vector;

public class Main {

    private Connection connection;
    private Statement statement;

    public Main() {
        this.connection = null;
        this.statement = null;
    }

    private void initConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/hr?user=root&password=");
        this.statement = this.connection.createStatement();
        this.statement.setQueryTimeout(15);
    }

    public List<String> getRegions() throws SQLException {
        List<String> regions = new Vector<>();
        this.initConnection();

        ResultSet resultSet = this.statement.executeQuery("SELECT * FROM regions");

        while (resultSet.next()) {
            regions.add(resultSet.getInt("region_id") + "   " + resultSet.getString("region_name"));
        }
        this.connection.close();
        return regions;
    }

    public boolean insertRegion(String regionName) throws SQLException {
        this.initConnection();
        int rowsIns = this.statement.executeUpdate("INSERT INTO regions VALUES (null, '" + regionName + "')");
        this.connection.close();
        return rowsIns == 1;
    }

    public static void main(String[] args) {
        Main main = new Main();

        try {
          List<String> regions = main.getRegions();
          regions.forEach((String reg) -> {
              System.out.println(reg);
          });
//
//            boolean isInsert = main.insertRegion("ECUADOR");
//            System.out.println(isInsert ? "Se inserto un registro" : "Fallo la inserción");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
