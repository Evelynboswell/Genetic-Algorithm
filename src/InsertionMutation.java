import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InsertionMutation {
    public Chromosome mutate(Chromosome chromosome, List<School> schools) {
        HashMap<Teacher, School> assignment = new HashMap<>(chromosome.getAssignment());
        Random rand = new Random();
        Teacher[] teachers = assignment.keySet().toArray(new Teacher[0]);

        int teacherIndex = rand.nextInt(teachers.length);
        Teacher selectedTeacher = teachers[teacherIndex];

        // Assign a new random school to the selected teacher
        School newSchool = schools.get(rand.nextInt(schools.size()));
        assignment.put(selectedTeacher, newSchool);

        return new Chromosome(assignment);
    }
}
