package meditrack.storage;

import meditrack.model.Personnel;
import meditrack.model.ReadOnlyMediTrack;
import meditrack.model.Role;
import meditrack.model.Supply;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class to export MediTrack data to a universally readable CSV format.
 */
public class CsvExportUtility {

    /**
     * Exports the application data to a CSV file based on the user's security clearance.
     *
     * @param data        The current read-only state of the application data.
     * @param currentRole The role of the user requesting the export.
     * @return The file path where the CSV was saved.
     * @throws IOException If there is an error writing to the file system.
     */
    public static Path exportData(ReadOnlyMediTrack data, Role currentRole) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String rolePrefix = currentRole.name();
        String fileName = rolePrefix + "_Export_" + timestamp + ".csv";
        Path exportDir = Paths.get(System.getProperty("user.dir"), "exports");

        if (!Files.exists(exportDir)) {
            Files.createDirectories(exportDir);
        }

        Path filePath = exportDir.resolve(fileName);

        try (FileWriter writer = new FileWriter(filePath.toFile())) {

            // --- 1. PERSONNEL ROSTER (RESTRICTED ACCESS) ---
            // Only MOs and Field Medics have clearance to export medical data
            if (currentRole == Role.MEDICAL_OFFICER || currentRole == Role.FIELD_MEDIC) {
                writer.append("=== PERSONNEL ROSTER ===\n");
                writer.append("Name,Status\n");
                for (Personnel p : data.getPersonnelList()) {
                    writer.append(String.format("\"%s\",\"%s\"\n", p.getName(), p.getStatus().toString()));
                }
                writer.append("\n");
            }

            // --- 2. SUPPLY INVENTORY (GLOBAL ACCESS) ---
            // All roles need access to supply data for operational readiness
            writer.append("=== SUPPLY INVENTORY ===\n");
            writer.append("Item Name,Quantity,Expiry Date\n");
            for (Supply s : data.getSupplyList()) {
                writer.append(String.format("\"%s\",%d,\"%s\"\n",
                        s.getName(),
                        s.getQuantity(),
                        s.getExpiryDate().toString()));
            }

            writer.flush();
        }
        return filePath;
    }
}