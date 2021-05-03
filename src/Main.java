import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        RegionController regionController = new RegionController();
        boolean isInsert = false;
        String messageError = "";
        try {
//            List<Region> regions = regionController.getRegions();
//            regions.forEach((Region reg) -> {
//                System.out.println(reg.getRegion());
//            });
//
            isInsert = regionController.insertRegion("PANAMÁ");
        } catch (SQLException e) {
            switch (e.getErrorCode()) {
                case 200:
                    messageError = "Region name no puede ser null";
                    break;
                case 1062:
                    messageError = "Ya existe una región con ese nombre";
                    break;
                default:
                    messageError = e.getMessage();
            }
        }
        System.out.println(isInsert ? "Region ingresada" : messageError);

    }
}
