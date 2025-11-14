package utils;

import java.util.ArrayList;
import java.util.List;

public class PrimeUtils {
    public List<Long> primeNumbersInRange(long startIndex, long endIndex) {
        List<Long> primeNumbers = new ArrayList<>();
        for (long i = startIndex; i <= endIndex; i++) {
            if(isPrime(i)) primeNumbers.add(i);
        }
        return primeNumbers;
    }

    private boolean isPrime(long number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        long limit = (long) Math.sqrt(number);
        for (long i = 3; i <= limit; i++) {
            if (number%i == 0) return false;
        }
        return true;
    }
}
