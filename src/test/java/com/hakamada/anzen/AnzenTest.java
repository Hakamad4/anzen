package com.hakamada.anzen;

import com.hakamada.anzen.core.v1.Anzen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AnzenTest {

    private static Anzen<Integer> integerCacheHolder = new Anzen<>(10);

    @BeforeAll
    public static void setUp() {
        integerCacheHolder.put("SECRET_PHRASE_1", 1);
        integerCacheHolder.put("SECRET_PHRASE_2", 2);
        integerCacheHolder.put("SECRET_PHRASE_3", 3);
        integerCacheHolder.put("SECRET_PHRASE_4", 4);
        integerCacheHolder.put("SECRET_PHRASE_5", 5);
        integerCacheHolder.put("SECRET_PHRASE_6", 6);
        integerCacheHolder.put("SECRET_PHRASE_7", 7);
        integerCacheHolder.put("SECRET_PHRASE_8", 8);
        integerCacheHolder.put("SECRET_PHRASE_9", 9);
        integerCacheHolder.put("SECRET_PHRASE_10", 10);
    }

    @Test
    public void testWhenMaxCapacityIsExceeded() {
        integerCacheHolder.put("SECRET_PHRASE_11", 11);
        Assertions.assertNull(integerCacheHolder.getByKey("SECRET_PHRASE_1"));
        Assertions.assertNotNull(integerCacheHolder.getByKey("SECRET_PHRASE_11"));
    }

    @Test
    public void testWhenOverrideAValue() {
        integerCacheHolder.put("SECRET_PHRASE_3", 11);
        Assertions.assertEquals(integerCacheHolder.getByKey("SECRET_PHRASE_3"), 11);
    }

    @Test
    public void testSuccessPath() {
        Assertions.assertEquals(integerCacheHolder.getByKey("SECRET_PHRASE_3"), 3);
    }

    @Test
    public void testSimulateAMultipleRequestSavingACache() throws InterruptedException {
        integerCacheHolder = new Anzen<>(100000);

        var aux = new Object() {
            int count = 0;
        };
        Thread[] threads = new Thread[50];
        for (int i = 0; i < threads.length; i++) {
            aux.count = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1500; j++) {
                    String phrase = "SECRET_PHRASE_" + (aux.count * ((int) (Math.random() * 100d))) + "_" + j;
                    integerCacheHolder.put(phrase, (int) (Math.random() * 100d));
                    System.out.println(phrase);
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("integerCacheHolder = " + integerCacheHolder);
    }
}
