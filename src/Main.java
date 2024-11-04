import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = "data_jarak.csv"; // Specify your CSV file path
        String line;
        String csvSplitBy = ";"; // Adjust this based on your CSV format

        List<Teacher> teachers = new ArrayList<>();
        List<School> schools = new ArrayList<>();
        List<String[]> allRecords = new ArrayList<>();

        // Read the CSV file and store all records
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                if (values.length < 4) continue; // Skip if the line doesn't have enough columns
                allRecords.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Shuffle records and select the first 100
        Collections.shuffle(allRecords);
        List<String[]> selectedRecords = allRecords.subList(0, Math.min(100, allRecords.size()));

        // Print the selected 100 records
        System.out.println("Selected 100 Records:");
        for (String[] values : selectedRecords) {
            System.out.println(String.join(", ", values));
        }
        System.out.println("--------------------------------------------------");

        // Process the selected records
        for (String[] values : selectedRecords) {
            String schoolName = values[1].trim();
            String teacherName = values[2].trim();

            // Add school if not already added
            if (schools.stream().noneMatch(s -> s.getName().equals(schoolName))) {
                schools.add(new School(schoolName));
            }

            // Add teacher to the list
            teachers.add(new Teacher(teacherName));
        }

        // Initialize the genetic algorithm
        GeneticAlgorithm ga = new GeneticAlgorithm(teachers, schools);
        ga.initializePopulation(); // Create an initial population
        ga.run(); // Run the genetic algorithm

        // Print out final population assignments
        System.out.println("Genetic Algorithm Final Population Assignments:");
        for (Chromosome chromosome : ga.getPopulation()) {
            System.out.println("Assignment:");
            HashMap<Teacher, School> assignment = chromosome.getAssignment();
            for (Teacher teacher : assignment.keySet()) {
                School school = assignment.get(teacher);
                System.out.println("Teacher: " + teacher + " -> School: " + school);
            }
            System.out.println("--------------------------------------------------");
        }
    }
}
