import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

/*
 Create a proxy object to count how many time a method is invoked.
*/
public class ProxyTest {
    public static void main(String[] args) throws Exception {
        BoxPair<String, Integer> original = new BoxPair<>(new Box<>(), new Box<>());
        AtomicInteger counter = new AtomicInteger();
        BoxPair<String, Integer> proxy = createProxy(original, counter);
        RandomInvoker.run(proxy);
        System.out.printf("getFirst was called %d times", counter.get());
    }

    private static <K,V> BoxPair<K, V> createProxy(BoxPair<K, V> original, AtomicInteger counter) throws Exception {
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(BoxPair.class);
        f.setFilter(m -> true);
        MethodHandler mi = (self, m, method, args) -> {
            if (m.getName().equals("getFirst")) {
                counter.incrementAndGet();
            }
            return m.invoke(original, args);  // execute the original method.
        };
        return (BoxPair<K, V>) f.create(new Class<?>[]{Box.class, Box.class}, new Object[]{null, null}, mi);
    }
}
