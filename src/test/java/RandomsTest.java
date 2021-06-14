import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RandomsTest {
    protected int min = 50;
    protected int max = 55;
    protected Randoms rand = new Randoms(50, 55);

    @Test
    void hasNext_alwaysTrue_test() {
        boolean res = true;
        Iterator<Integer> iter = rand.iterator();
        for (int i = 0; i < 100; i++) {
            if (!iter.hasNext()) {
                res = false;
            }
            iter.next();
        }
        assertTrue(res);
    }

    @Test
    void next_alwaysInRange_test() {
        int i = 0;
        boolean res = true;
        for (int r : new Randoms(min, max)) {
            if (r < min || r > max) {
                res = false;
            }
            if (i == 100) {
                break;
            }
            i++;
        }
        assertTrue(res);
    }

    @Test
    void next_alwaysInRange_negative_test1() {
        int i = 0;
        boolean res = true;
        for (int r : new Randoms(min * (-1), max)) {
            if (r < min * (-1) || r > max) {
                res = false;
            }
            if (i == 100) {
                break;
            }
            i++;
        }
        assertTrue(res);
    }

    @Test
    void next_alwaysInRange_negative_test2() {
        int i = 0;
        boolean res = true;
        for (int r : new Randoms(max * (-1), min * (-1))) {
            if (r < max * (-1) || r > min * (-1)) {
                res = false;
            }
            if (i == 100) {
                break;
            }
            i++;
        }
        assertTrue(res);
    }

    @Test
    void next_alwaysInRange_maxLessThenMin_test3() {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () ->
                new Randoms(max, min)
        );
        assertEquals(String.format("Некорректные параметры диапазона: max должен быть больше min!"), result.getMessage());
    }
}
