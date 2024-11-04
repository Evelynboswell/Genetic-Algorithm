import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UniqueValueCounter {
    public static void main(String[] args) {
        String csvFile = "D:/Genetic-Algorithm/data_jarak.csv"; // Replace with your CSV file path
        int columnIndex = 2; // Replace with the column index you want to check (0-based index)

        try {
            Set<String> uniqueValues = new HashSet<>();
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;

            // Read the header line (uncomment if you have a header)
            br.readLine(); // Uncomment if there's a header

            // Read each line from the CSV file
            while ((line = br.readLine()) != null) {
                // Split the line by commas (CSV delimiter)
                String[] values = line.split(";");

                if (columnIndex < values.length) {
                    // Convert the value to uppercase before adding it to the set
                    String value = values[columnIndex].toUpperCase();
                    uniqueValues.add(value);
                }
            }
            br.close();

            // Display unique values one line at a time
            System.out.println("Unique uppercase values in column " + columnIndex + ":");
            for (String value : uniqueValues) {
                System.out.println(value);
            }

            // Print the count of unique values
            System.out.println("Count of unique values: " + uniqueValues.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
