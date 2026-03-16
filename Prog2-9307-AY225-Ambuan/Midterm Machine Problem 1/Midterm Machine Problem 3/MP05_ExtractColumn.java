import java.io.*;
import java.util.*;

public class MP05_ExtractColumn {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter dataset file path: ");
        String path = input.nextLine();

        System.out.print("Enter column number (starting from 0): ");
        int column = input.nextInt();

        try {

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                if (column < values.length) {
                    System.out.println(values[column]);
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }

        input.close();
    }
}