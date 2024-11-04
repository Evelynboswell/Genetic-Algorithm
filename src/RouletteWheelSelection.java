import java.util.List;
import java.util.Random;

public class RouletteWheelSelection {
    public Teacher select(List<Teacher> teachers, double[] fitness) {
        double totalFitness = 0;
        for (double fit : fitness) {
            totalFitness += fit;
        }

        double rand = new Random().nextDouble() * totalFitness;
        double cumulativeFitness = 0;

        for (int i = 0; i < teachers.size(); i++) {
            cumulativeFitness += fitness[i];
            if (cumulativeFitness >= rand) {
                return teachers.get(i);
            }
        }

        return null; // Should not reach here if fitness values are positive
    }
}
