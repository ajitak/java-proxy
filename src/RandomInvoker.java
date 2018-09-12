import java.util.stream.IntStream;

public class RandomInvoker {
    public static void run(BoxPair<?, ?> pair) {
        IntStream.range(1, 100).forEach(i -> {
            if (Math.random() < 0.5) {
                pair.getFirst();
            } else {
                pair.getSecond();
            }
        });
    }
}
