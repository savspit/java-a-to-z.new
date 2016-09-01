import org.junit.*;
import ru.shestakov.models.*;
import ru.shestakov.start.*;

/**
 * Init StartUITest class
 */
public class StartUITest {

    /**
     * Test for add new Item
     * @throws Exception
     */
    @Test
    public void testAddItem() throws Exception {

        Tracker tracker = new Tracker();

        String action = "0";
        String yes = "y";
        String no = "n";

        String name = "task1";
        String desc = "desc1";
        String create = "3724";

        StubInput input = new StubInput(new String[]{action, name, desc, create, yes});
        new StartUI(input).init(tracker);

        for (Item item : tracker.getAll()) {
            if (item != null) {
                Assert.assertEquals(item.getName(), name);
            }
        }

    }

    /**
     * Test for update Item
     * @throws Exception
     */
    /*@Test
    public void testUpdateItem() throws Exception {
        Tracker tracker = new Tracker();
        String action = "0";
        String yes = "y";
        String no = "n";
        // add first item
        String name1 = "task1";
        String desc1 = "desc1";
        String create1 = "3724";
        Input input1 = new TestInput(new String[]{action, name1, desc1, create1, yes});
        new StartUI(input1).init(tracker);
        // add second item
        String name2 = "task2";
        String desc2 = "desc2";
        String create2 = "97689";
        Input input2 = new TestInput(new String[]{action, name2, desc2, create2, yes});
        new StartUI(input2).init(tracker);
        // get first item id
        String id = "";
        Filter filter = new FilterByName(name1);
        Item[] result = tracker.findBy(filter);
        for (Item item : result) {
            if(item != null && item.getName().equals(name1)) {
                id = item.getId();
                break;
            }
        }
        // update first item
        String action2 = "1";
        String name3 = "updated task";
        String desc3 = "updated desc";
        String create3 = "46754";
        Input input3 = new TestInput(new String[]{action2, id, name3, desc3, create3, yes});
        new StartUI(input3).init(tracker);
        Item foundedItem = tracker.findById(id);
        Assert.assertEquals(name3, foundedItem.getName());
    }*/

    /**
     * Test for delete Item
     * @throws Exception
     */
    /*@Test
    public void testDeleteItem() throws Exception {
        Tracker tracker = new Tracker();
        String action = "0";
        String yes = "y";
        String no = "n";
        // add first item
        String name1 = "task1";
        String desc1 = "desc1";
        String create1 = "3724";
        Input input1 = new TestInput(new String[]{action, name1, desc1, create1, yes});
        new StartUI(input1).init(tracker);
        // add second item
        String name2 = "task2";
        String desc2 = "desc2";
        String create2 = "97689";
        Input input2 = new TestInput(new String[]{action, name2, desc2, create2, yes});
        new StartUI(input2).init(tracker);
        // get first item id
        String id = "";
        Filter filter = new FilterByName(name1);
        Item[] result = tracker.findBy(filter);
        for (Item item : result) {
            if (item !=null && item.getName().equals(name1)) {
                id = item.getId();
                break;
            }
        }
        // delete first item
        String action2 = "2";
        Input input3 = new TestInput(new String[]{action2, id, yes});
        new StartUI(input3).init(tracker);
        Assert.assertNull(tracker.findById(id));
    }*/

    /**
     * Test for add comment to Item
     * @throws Exception
     */
    /*@Test
    public void testAddComment() throws Exception {
        Tracker tracker = new Tracker();
        String action = "0";
        String yes = "y";
        String no = "n";
        // add item
        String name = "task1";
        String desc = "desc1";
        String create = "3724";
        Input input1 = new TestInput(new String[]{action, name, desc, create, yes});
        new StartUI(input1).init(tracker);
        // get item id
        String id = "";
        Filter filter = new FilterByName(name);
        Item[] result = tracker.findBy(filter);
        for (Item item : result) {
            if (item !=null && item.getName().equals(name)) {
                id = item.getId();
                break;
            }
        }
        // add comment to item
        String action2 = "3";
        String comment = "new comment";
        Input input2 = new TestInput(new String[]{action2, comment, yes});
        new StartUI(input2).init(tracker);
        for (Comment nextComment : tracker.findById(id).getComments()) {
            if(nextComment != null) {
                Assert.assertEquals(nextComment.getText(), comment);
                break;
            }
        }
    }*/

    /**
     * Test for find Item by name
     * @throws Exception
     */
    /*@Test
    public void testFindByName() throws Exception {
        Tracker tracker = new Tracker();
        String action = "0";
        String yes = "y";
        String no = "n";
        // add item
        String name = "task1";
        String desc = "desc1";
        String create = "3724";
        Input input1 = new TestInput(new String[]{action, name, desc, create, yes});
        new StartUI(input1).init(tracker);
        // find item
        String action2 = "4";
        Input input2 = new TestInput(new String[]{action2, name, yes});
        new StartUI(input2).init(tracker);
    }*/

    /**
     * Test for find Item by description
     * @throws Exception
     */
    /*@Test
    public void testFindByDescription() throws Exception {
        Tracker tracker = new Tracker();
        String action = "0";
        String yes = "y";
        String no = "n";
        // add item
        String name = "task1";
        String desc = "desc1";
        String create = "3724";
        Input input1 = new TestInput(new String[]{action, name, desc, create, yes});
        new StartUI(input1).init(tracker);
        // find item
        String action2 = "5";
        Input input2 = new TestInput(new String[]{action2, desc, yes});
        new StartUI(input2).init(tracker);
    }*/

    /**
     * Test for find Item by date
     * @throws Exception
     */
    /*@Test
    public void testFindByCreate() throws Exception {
        Tracker tracker = new Tracker();
        String action = "0";
        String yes = "y";
        String no = "n";
        // add item
        String name = "task1";
        String desc = "desc1";
        String create = "3724";
        Input input1 = new TestInput(new String[]{action, name, desc, create, yes});
        new StartUI(input1).init(tracker);
        // find item
        String action2 = "5";
        Input input2 = new TestInput(new String[]{action2, create, yes});
        new StartUI(input2).init(tracker);
    }*/

}