package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUtility {

    public List<Integer> createRandomIntegerList(int size, int bound) {
        Random r = new Random();
        List<Integer> intList = new ArrayList<>(size);
        int i = 0;
        while (i < size) {
            intList.add(r.nextInt(bound));
            i++;
        }
        return intList;
    }

    public List<Integer> copyIntegerList(List<Integer> sourceList) {
        List<Integer> destList = new ArrayList<>(sourceList.size());
        int i = 0;
        while (i < sourceList.size()) {
            destList.add(sourceList.get(i));
            i++;
        }
        return destList;
    }
}
