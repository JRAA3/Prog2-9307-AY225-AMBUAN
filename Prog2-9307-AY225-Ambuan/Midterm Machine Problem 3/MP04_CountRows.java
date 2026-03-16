import java.io.*;
import java.util.*;

public class MP04_CountRows {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter dataset file path: ");
        String path = input.nextLine();

        int validRows = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            while ((line = br.readLine()) != null) {

                // Check if row is not empty
                if (!line.trim().isEmpty()) {
                    validRows++;
                }
            }

            br.close();

            System.out.println("Total Valid Rows: " + validRows);

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }

        input.close();
    }
}