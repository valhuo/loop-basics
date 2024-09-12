package loopbasics;

import java.util.ArrayList;
import java.util.List;

// This class provids two different implementations of the Collatz sequence:
// https://en.wikipedia.org/wiki/Collatz_conjecture
//
public class Collatz {
    private static List<Integer> collatzWithWhile(int n) {
        // This implementation uses a while loop.

        List<Integer> result = new ArrayList<>();
        while (n > 1) {
            result.add(n);
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
        }
        return result;
    }

    private static List<Integer> collatzWithFor(int n) {
        // This implementation uses (or arguably misuses) a C-style foor loop.
        // (No need to try to understand all the details unless you are feeling particularly
        // masochistic. Just soak up the general flavor of this code.)

        List<Integer> result;
        for (result = new ArrayList<>(); n > 1; result.add(n), n = (n % 2 == 0) ? n / 2 : 3 * n + 1);
        return result;
    }

    // Which one takes less code?
    //
    // Which would you rather read? Which would you rather have to make sense of?
    //
    // There _are_ ways to make the second implementation less hideous while still using a C-style
    // for loop. That is an especially egregious piece of code there! However, for this particular
    // problem, cleverness doesn’t improve things. The step-by-style clarity of the while loop
    // implementation is hard to beat, even if it is less concise.

    public static void main(String[] args) {
        System.err.println(collatzWithWhile(2345));
        System.err.println(collatzWithFor(2345));
    }
}
