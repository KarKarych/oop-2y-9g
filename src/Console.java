import my.map.MyHashMap;
import my.map.MyMap;

import java.util.UUID;

public class Console {
    public static void main(String[] args) {
        MyMap<String, String> map = new MyHashMap<>();

        String key1 = UUID.randomUUID().toString();
        String key2 = UUID.randomUUID().toString();
        String key3 = UUID.randomUUID().toString();

        System.out.println(key1);
        System.out.println(key2);
        System.out.println(key3);

        System.out.println();

        map.add(key1, "1");
        map.add(key2, "2");
        map.add(key3, "3");

        System.out.println(map.get(key1));
        System.out.println(map.get(key2));
        System.out.println(map.get(key3));

        System.out.println();

        System.out.println(map.size());

        System.out.println();

        map.delete(key2);

        System.out.print(map.get(key2));
    }
}