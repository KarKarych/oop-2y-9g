package my.map;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Node<K, V> {

    private final List<Node<K, V>> nodes;
    K key;
    V value;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
        nodes = new LinkedList<>();
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    List<Node<K, V>> getNodes() {
        return nodes;
    }

    void setValue(V value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof Node) {
            @SuppressWarnings({"rawtypes", "unchecked"})
            Node<K, V> node = (Node<K, V>) object;

            return Objects.equals(key, node.getKey())
                    && Objects.equals(value, node.getValue())
                    || Objects.equals(hashCode(), node.hashCode());
        }

        return false;
    }
}