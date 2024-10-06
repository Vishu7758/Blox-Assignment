package org.vishal;

import java.util.*;

public class DatabaseMigrationVerifier {

    public static boolean validateMigration(List<Map<String, String>> originalData, List<Map<String, String>> migratedData) {
        if (originalData.size() != migratedData.size()) {
            System.out.println("Size mismatch");
            return false;
        }

        for (int i = 0; i < originalData.size(); i++) {
            Map<String, String> originalRow = originalData.get(i);
            Map<String, String> migratedRow = migratedData.get(i);

            if (!checkRow(originalRow, migratedRow)) {
                System.out.println("Data mismatch at row " + i);
                System.out.println("Original row " + originalRow);
                System.out.println("Migrated row " + migratedRow);
                return false;
            }
        }
        return true;
    }

    private static boolean checkRow(Map<String, String> row1, Map<String, String> row2) {
        if (!row1.keySet().equals(row2.keySet())) {
            return false;
        }

        return row1.keySet().stream() //
                .allMatch(key -> row1.get(key).equals(row2.get(key)));
    }

    public static void main(String[] args) {
        List<Map<String, String>> originalData = new ArrayList<>();
        List<Map<String, String>> migratedData = new ArrayList<>();

        Map<String, String> row1 = new HashMap<>();
        row1.put("city", "Kolhapur");
        row1.put("company", "Blox");
        originalData.add(row1);
        migratedData.add(new HashMap<>(row1));

        boolean isIdentical = validateMigration(originalData, migratedData);
        System.out.println("Migration valid? " + isIdentical);
    }
}
