import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

public class Main {
    
    // Inner class to represent a game record
    static class DataRecord {
        String title;
        String releaseDate;
        double totalSales;
        
        DataRecord(String title, String releaseDate, double totalSales) {
            this.title = title;
            this.releaseDate = releaseDate;
            this.totalSales = totalSales;
        }
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File file;
        
        // File validation loop
        while (true) {
            System.out.print("Enter dataset file path: ");
            String path = input.nextLine();
            
            file = new File(path);
            
            if (file.exists() && file.isFile()) {
                System.out.println("File found! Processing...\n");
                break;
            } else {
                System.out.println("Invalid file path. Please try again.\n");
            }
        }
        
        try {
            // Read and process CSV
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean isHeader = true;
            
            // Map to store monthly totals: month -> total sales
            HashMap<String, Double> monthlySales = new HashMap<>();
            
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; // Skip header row
                }
                
                try {
                    DataRecord record = parseCSVLine(line);
                    if (record != null && !record.releaseDate.isEmpty()) {
                        String month = record.releaseDate.substring(0, 7); // Extract YYYY-MM
                        monthlySales.put(month, monthlySales.getOrDefault(month, 0.0) + record.totalSales);
                    }
                } catch (Exception e) {
                    // Skip malformed lines
                    continue;
                }
            }
            
            reader.close();
            
            // Sort by month (ascending)
            TreeMap<String, Double> sortedMonthlySales = new TreeMap<>(monthlySales);
            
            // Display results
            System.out.println("=== MONTHLY PERFORMANCE SUMMARY ===");
            System.out.println(String.format("%-12s %s", "Month", "Total Sales"));
            System.out.println("=====================================");
            
            String bestMonth = "";
            double bestSales = 0.0;
            
            for (Map.Entry<String, Double> entry : sortedMonthlySales.entrySet()) {
                System.out.println(String.format("%-12s %.2f", entry.getKey(), entry.getValue()));
                
                if (entry.getValue() > bestSales) {
                    bestSales = entry.getValue();
                    bestMonth = entry.getKey();
                }
            }
            
            System.out.println("=====================================");
            System.out.println(String.format("\nBest Performing Month: %s with %.2f total sales", bestMonth, bestSales));
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            input.close();
        }
    }
    
    // Parse a CSV line and extract relevant fields
    static DataRecord parseCSVLine(String line) {
        // Handle CSV parsing with proper quote handling
        String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        
        if (parts.length < 13) {
            return null;
        }
        
        try {
            String title = parts[1].replaceAll("^\"|\"$", "");
            String releaseDate = parts[12].trim();
            double totalSales = Double.parseDouble(parts[7].trim());
            
            return new DataRecord(title, releaseDate, totalSales);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
