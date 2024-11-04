import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
    private List<Teacher> teachers;
    private List<School> schools;
    private List<Chromosome> population;
    private final int POPULATION_SIZE = 10;
    private final double MUTATION_RATE = 0.1;

    public GeneticAlgorithm(List<Teacher> teachers, List<School> schools) {
        this.teachers = teachers;
        this.schools = schools;
        this.population = new ArrayList<>();
    }

    public void initializePopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            HashMap<Teacher, School> assignment = new HashMap<>();
            for (Teacher teacher : teachers) {
                // Randomly assign schools
                School randomSchool = schools.get(new Random().nextInt(schools.size()));
                assignment.put(teacher, randomSchool);
            }
            population.add(new Chromosome(assignment));
        }
    }

    public void run() {
        // Initialize selection, crossover, and mutation
        InsertionMutation mutation = new InsertionMutation();
        CycleCrossover crossover = new CycleCrossover();
        for (int generation = 0; generation < 10; generation++) {
            List<Chromosome> newPopulation = new ArrayList<>();
            for (int i = 0; i < population.size() / 2; i++) {
                Chromosome parent1 = population.get(new Random().nextInt(population.size()));
                Chromosome parent2 = population.get(new Random().nextInt(population.size()));

                Chromosome child = crossover.crossover(parent1, parent2, teachers);
                newPopulation.add(child);

                // Apply mutation
                if (Math.random() < MUTATION_RATE) {
                    newPopulation.add(mutation.mutate(child, schools));
                }
            }
            population = newPopulation; // Replace the old population
        }
    }

    public List<Chromosome> getPopulation() {
        return population;
    }
}
