import java.sql.*;
import java.util.List;
import java.util.Vector;

public class RegionController {
    private Connection connection;
    private Statement statement;

    public RegionController() {
        this.connection = null;
        this.statement = null;
    }

    private void initConnection() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/hr?user=hr&password=hr");
        this.statement = this.connection.createStatement();
        this.statement.setQueryTimeout(15);
    }

    public List<Region> getRegions() throws SQLException {
        List<Region> regions = new Vector<>();
        this.initConnection();

        ResultSet resultSet = this.statement.executeQuery("SELECT * FROM regions");

        while (resultSet.next()) {
            Region region = new Region(
                    resultSet.getInt("region_id"),
                    resultSet.getString("region_name"),
                    resultSet.getString("region_image")
            );
            regions.add(region);
        }
        this.connection.close();
        return regions;
    }

    public boolean insertRegion(String regionName) throws SQLException {
        if(regionName == null){
            throw new SQLException("Region name no puede ser null", "INSERT", 200);
        }
        this.initConnection();
        int rowsIns = this.statement.executeUpdate("INSERT INTO regions VALUES (null, '" + regionName + "', null)");
        this.connection.close();
        return rowsIns == 1;
    }

}
