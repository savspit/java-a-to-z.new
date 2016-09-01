package ru.shestakov.start;

import org.junit.Assert;
import org.junit.Test;
import ru.shestakov.models.Item;

import static org.junit.Assert.*;

public class StartUIPerfomanceTest {

    @Test
    public void testAddDeleteItem() throws Exception {

        Tracker tracker = new Tracker();

        String action = "0";
        String yes = "y";
        String no = "n";

        String name = "task1";
        String desc = "desc1";
        String create = "3724";

        StubInput input = new StubInput(new String[]{action, name, desc, create, yes});
        new StartUI(input).init(tracker);

        /*for (Item item : tracker.getAll()) {
            if (item != null) {
                Assert.assertEquals(item.getName(), name);
            }
        }*/


    }

}