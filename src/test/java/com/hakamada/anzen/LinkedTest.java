package com.hakamada.anzen;

import com.hakamada.anzen.core.list.Linked;
import com.hakamada.anzen.model.CachedItem;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LinkedTest {

    private static Linked<String> linked = new Linked<>();

    @BeforeAll
    public static void setUp() {
        linked.add("KEY_1", "LUFFY_1");
        linked.add("KEY_2", "LUFFY_2");
        linked.add("KEY_3", "LUFFY_3");
        linked.add("KEY_4", "LUFFY_4");
        linked.add("KEY_5", "LUFFY_5");
        linked.add("KEY_6", "LUFFY_6");
        linked.add("KEY_7", "LUFFY_7");
        linked.add("KEY_8", "LUFFY_8");
        linked.add("KEY_9", "LUFFY_9");
        linked.add("KEY_10", "LUFFY_10");
        linked.add("KEY_11", "LUFFY_11");
        linked.add("KEY_12", "LUFFY_12");
        linked.add("KEY_14", "LUFFY_14");
        linked.add("KEY_15", "LUFFY_15");
        linked.add("KEY_16", "LUFFY_16");
    }
    @Test
    public void testGetFirstItem() {
        Assertions.assertEquals(linked.getFirst().getContent(),"LUFFY_1");
        Assertions.assertEquals(linked.getFirst().getKey(),"KEY_1");
    }

    @Test
    public void testGetLastItem() {
        Assertions.assertEquals(linked.getLast().getContent(),"LUFFY_16");
        Assertions.assertEquals(linked.getLast().getKey(),"KEY_16");
    }

    @Test
    @Order(1)
    public void testGetSize() {
        Assertions.assertEquals(linked.getSize(), 15);
    }

    @Test
    public void testGetByKey() {
        CachedItem<String> stringCachedItem = linked.get("KEY_5");
        Assertions.assertEquals(stringCachedItem.getContent(),"LUFFY_5");
    }

    @Test
    public void testRemoveByKey() {
        linked.remove("KEY_5");
        Assertions.assertNull(linked.get("KEY_5"));
        Assertions.assertEquals(linked.get("KEY_4").getNextItem().getKey(), "KEY_6");
    }

    @Test
    public void testOverrideValue() {
        linked.add("KEY_16", "LUFFY_100");
        Assertions.assertEquals(linked.get("KEY_16"), "LUFFY_100");
    }
}
