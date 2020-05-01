package azmain.github.io.util;

public interface ResultMapper<K, V> {
    V map(K k);
}
