import java.util.HashMap;

public class Chromosome {
    private HashMap<Teacher, School> assignment;

    public Chromosome(HashMap<Teacher, School> assignment) {
        this.assignment = assignment;
    }

    public HashMap<Teacher, School> getAssignment() {
        return assignment;
    }
}
