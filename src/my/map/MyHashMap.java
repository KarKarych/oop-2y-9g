package my.map;

import java.util.List;
import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {

    private Node<K, V>[] hashTable;
    private int size = 0;
    private double threshold;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        double loadFactor = 0.5;

        hashTable = (Node<K, V>[]) new Node[16];
        threshold = hashTable.length * loadFactor;
    }

    @Override
    public V get(K key) {
        int index = hash(key);

        if (index >= hashTable.length)
            throw new ArrayIndexOutOfBoundsException();

        if (hashTable[index] == null)
            return null;

        List<Node<K, V>> listNodes = hashTable[index].getNodes();

        for (Node<K, V> node : listNodes) {
            if (key.equals(node.getKey())) {
                return node.getValue();
            }
        }

        return null;
    }

    @Override
    public void add(K key, V value) {
        if (threshold <= size + 1) {
            threshold *= 2;
            resize();
        }

        Node<K, V> node = new Node<>(key, value);
        int index = hash(key);

        if (hashTable[index] == null) {
            simpleAdd(index, node);
            return;
        }

        List<Node<K, V>> listNodes = hashTable[index].getNodes();
        for (Node<K, V> nodeFromList : listNodes) {
            if (keyExistsButValueNew(nodeFromList, node) || collisionProcessing(nodeFromList, node, listNodes)) {
                return;
            }
        }
    }

    @Override
    public void delete(K key) {
        int index = hash(key);
        if (hashTable[index] == null) {
            return;
        }

        if (hashTable[index].getNodes().size() == 1) {
            hashTable[index] = null;
            return;
        }

        List<Node<K, V>> nodeList = hashTable[index].getNodes();
        for (Node<K, V> node : nodeList) {
            if (key.equals(node.getKey())) {
                nodeList.remove(node);
                return;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldHashTable = hashTable;
        hashTable = (Node<K, V>[]) new Node[oldHashTable.length * 2];
        size = 0;

        for (Node<K, V> node : oldHashTable) {
            if (node != null) {
                for (Node<K, V> nextNode : node.getNodes()) {
                    add(nextNode.key, nextNode.value);
                }
            }
        }
    }

    private boolean keyExistsButValueNew(Node<K, V> nodeFromList, Node<K, V> nextNode) {
        if (nextNode.getKey().equals(nodeFromList.getKey()) && !nextNode.getValue().equals(nodeFromList.getValue())) {
            nodeFromList.setValue(nextNode.getValue());
            return true;
        }
        return false;
    }

    private boolean collisionProcessing(Node<K, V> nodeFromList, Node<K, V> nextNode, List<Node<K, V>> nodes) {
        if (hash(nodeFromList.getKey()) == hash(nextNode.getKey())
                && !Objects.equals(nodeFromList.key, nextNode.key)
                && !Objects.equals(nodeFromList.value, nextNode.value)) {
            nodes.add(nextNode);
            size++;
            return true;
        }
        return false;
    }

    private void simpleAdd(int index, Node<K, V> node) {
        hashTable[index] = new Node<>(null, null);
        hashTable[index].getNodes().add(node);
        size++;
    }


    private int hash(K key) {
        return key.hashCode() & (hashTable.length - 1);
    }
}
