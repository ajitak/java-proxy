/*
 Wrapper around Box class.
*/
public class BoxPair<K, V> {
    private final Box<K> first;
    private final Box<V> second;
    public BoxPair(Box<K> first, Box<V> second) {
        this.first = first;
        this.second = second;
    }

    public Box<K> getFirst() {
        return this.first;
    }

    public Box<V> getSecond() {
        return this.second;
    }
}