import java.util.HashMap;
import java.util.List;

public class CycleCrossover {
    public Chromosome crossover(Chromosome parent1, Chromosome parent2, List<Teacher> teachers) {
        HashMap<Teacher, School> offspringAssignment = new HashMap<>(parent1.getAssignment());
        boolean[] visited = new boolean[teachers.size()];
        int index = 0;

        while (index < teachers.size()) {
            if (!visited[index]) {
                Teacher teacher = teachers.get(index);
                School initialSchoolFromParent1 = parent1.getAssignment().get(teacher);
                School schoolFromParent1 = initialSchoolFromParent1;

                // Cycle crossover logic
                while (true) {
                    offspringAssignment.put(teacher, schoolFromParent1);
                    visited[index] = true;

                    // Find the next teacher with the same school in parent2
                    boolean foundNextTeacher = false;
                    for (Teacher nextTeacher : teachers) {
                        // Check if this teacher is in parent2 and assigned to the same school
                        if (parent2.getAssignment().get(nextTeacher).equals(schoolFromParent1) && !visited[teachers.indexOf(nextTeacher)]) {
                            index = teachers.indexOf(nextTeacher);
                            teacher = nextTeacher;
                            schoolFromParent1 = parent1.getAssignment().get(teacher);
                            foundNextTeacher = true;
                            break; // Found the next teacher, break the loop
                        }
                    }

                    // If no next teacher found or we have visited all, exit
                    if (!foundNextTeacher || visited[index]) {
                        break;
                    }
                }
            }
            index++;
        }

        return new Chromosome(offspringAssignment);
    }
}
