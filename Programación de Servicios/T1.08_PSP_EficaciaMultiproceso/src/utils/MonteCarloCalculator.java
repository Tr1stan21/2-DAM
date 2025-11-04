package utils;

import java.util.Random;

public class MonteCarloCalculator {
    public static double monteCarlo(int drops) {
        Random random = new Random();
        int insideCircle = 0;

        for (int i = 0; i < drops; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (x * x + y * y <= 1) {
                insideCircle++;
            }
        }

        return 4.0 * insideCircle / drops;
    }
}
