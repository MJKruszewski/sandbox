package pl.sunflux.utils;

import java.util.HashSet;
import java.util.Set;

public class GameRandom {
    public static <E> E getRandomElement(Set<E> list) {
        int size = list.size();
        int item = new java.util.Random().nextInt(size);
        int i = 0;

        for (E obj : list) {
            if (i == item)
                return obj;
            i++;
        }

        return null;
    }

    public static <E> Set<E> getRandomElement(Set<E> list, int totalItems) {
        HashSet<E> newList = new HashSet<>();

        for (int i = 0; i < totalItems; i++) {
            E randomElement = getRandomElement(list);
            list.remove(randomElement);

            newList.add(randomElement);
        }

        return newList;
    }
}
