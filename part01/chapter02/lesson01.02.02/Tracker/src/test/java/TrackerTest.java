import org.junit.*;
import ru.shestakov.models.*;
import ru.shestakov.start.*;

public class TrackerTest {

    @Test
    public void testAdd() throws Exception {

        Tracker tracker = new Tracker();
        Task task1 = new Task("first task", "first desc", System.nanoTime());

        Assert.assertNotNull(tracker.add(task1));

    }

    @Test
    public void testUpdate() throws Exception {

        Tracker tracker = new Tracker();

        Task task1 = new Task("second task", "second desc", System.nanoTime());
        tracker.add(task1);

        Task taskToUpdate = new Task("updated task", task1.getDescription(), task1.getCreate());
        taskToUpdate.setId(task1.getId());
        tracker.update(taskToUpdate);

        Item findedItem = tracker.findByIdTest(task1.getId());

        Assert.assertNotNull(taskToUpdate);
        Assert.assertEquals(taskToUpdate.getName(), findedItem.getName());

    }

    @Test
    public void testDelete() throws Exception {

        Tracker tracker = new Tracker();

        Task task1 = new Task("first task", "first desc", System.nanoTime());
        tracker.add(task1);

        Assert.assertNotNull(tracker.findByIdTest(task1.getId()));

        tracker.delete(task1);

        Assert.assertNull(tracker.findByIdTest(task1.getId()));

    }

    @Test
    public void testAddComment() throws Exception {

        Tracker tracker = new Tracker();

        Task task1 = new Task("first task", "first desc", System.nanoTime());
        tracker.add(task1);

        Comment comment = new Comment(task1.getId(), "Some kind of comment");

        tracker.addComment(comment);

        for (Comment nextComment : task1.getComments()) {
            if(nextComment != null) {
                Assert.assertEquals(nextComment, comment);
                break;
            }
        }

    }
}
