package ru.shestakov.start;

import ru.shestakov.models.*;
import ru.shestakov.templates.*;

import java.sql.SQLException;

/**
 * The type Update item.
 */
class UpdateItem extends BaseAction {

    /**
     * Instantiates a new Update item.
     */
    UpdateItem() {
        super("Edit the new item.");
    };

    public int key() {
        return 1;
    }

    public void execute(Input input, Tracker tracker) throws SQLException {
        String id = input.ask("Please, enter the task`s id: ");

        if(tracker.findById(id) != null) {

            String name = input.ask("Please, enter the task`s name: ");
            String desc = input.ask("Please, enter the task`s desc: ");
            long create = input.ask("Please, enter the task`s date: ", true);

            Task task = new Task(name, desc, create);
            task.setId(id);
            tracker.update(task);

        } else if(id.equals("b")) {
            //nothing to do here
        } else {
            System.out.println("ID does not exists. Enter the task`s id or 'b' to go back");
            execute(input,tracker);
        }

    }

}

/**
 * The type Menu tracker.
 */
public class MenuTracker {

    private Input input;
    private Tracker tracker;
    /**
     * The Actions.
     */
    protected UserAction[] actions;
    private int position = 0;

    /**
     * Instantiates a new Menu tracker.
     *
     * @param input   the input
     * @param tracker the tracker
     * @param size    the size
     */
    public MenuTracker(Input input, Tracker tracker, int size) {
        this.input = input;
        this.tracker = tracker;
        this.actions = new UserAction[size];
    }

    /**
     * Fill actions.
     */
    public void fillActions() {
        this.actions[position++] = this.new AddItem();
        this.actions[position++] = new UpdateItem();
        this.actions[position++] = new DeleteItem();
        this.actions[position++] = new AddCommentToItem();
        this.actions[position++] = new FindItemByName();
        this.actions[position++] = new FindItemByDescription();
        this.actions[position++] = new FindItemByCreate();
        this.actions[position++] = new MenuTracker.ShowItems();
    }

    /**
     * Add action.
     *
     * @param action the action
     */
    public void addAction(UserAction action) {
        this.actions[position++] = action;
    }

    /**
     * Get ranges int [ ].
     *
     * @return the int [ ]
     */
    public int[] getRanges() {
        int[] ranges = new int[this.actions.length];
        for (int index=0; index<this.actions.length; index++) {
            ranges[index] = index;
        }
        return ranges;
    }

    /**
     * Select.
     *
     * @param key the key
     * @throws SQLException the sql exception
     */
    public void select(int key) throws SQLException {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Show.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) { this.showItem(action); }
        }
    }

    /**
     * Show item.
     *
     * @param action the action
     */
    public void showItem(UserAction action) {
        System.out.println(action.info());
    }

    private class AddItem extends BaseAction {

        /**
         * Instantiates a new Add item.
         */
        AddItem() {
            super("Add the new item.");
        };

        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) throws SQLException {
            String name = input.ask("Please, enter the task`s name: ");
            String desc = input.ask("Please, enter the task`s desc: ");
            long create = input.ask("Please, enter the task`s date: ", true);

            tracker.add(new Task(name, desc, create));
        }

    }

    private class DeleteItem extends BaseAction {

        /**
         * Instantiates a new Delete item.
         */
        DeleteItem() {
            super("Delete the item.");
        };

        public int key() {
            return 2;
        }

        public void execute(Input input, Tracker tracker) throws SQLException {
            String id = input.ask("Please, enter the task`s id: ");

            if(tracker.findById(id) != null) {
                Task task = new Task();
                task.setId(id);
                tracker.delete(task);

            } else {
                System.out.println("ID does not exists. Enter the task`s id again");
            }
        }

    }

    private class AddCommentToItem extends BaseAction {

        /**
         * Instantiates a new Add comment to item.
         */
        AddCommentToItem() {
            super("Add comment to the item.");
        };

        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) throws SQLException {
            String id = input.ask("Please, enter the task`s id: ");

            if (tracker.findById(id) != null) {

                String inputComment = input.ask("Please, enter the new comment to add to " + id + " task: ");

                Comment comment = new Comment(id, inputComment);
                tracker.addComment(comment);

            } else {
                System.out.println("ID does not exists. Enter the task`s id again");
            }

        }

    }

    private class FindItemByName extends BaseAction {

        /**
         * Instantiates a new Find item by name.
         */
        FindItemByName() {
            super("Find item by name.");
        };

        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) throws SQLException {
            String name = input.ask("Please, enter the task`s name to find: ");

            Item[] result = tracker.findByName(name);

            if (result.length != 0) {
                for (Item item : result) {
                    if (item != null) { System.out.println(item.getId()); }
                }
            } else {
                System.out.println("No matches. Enter the task`s name again");
            }
        }

    }

    private class FindItemByDescription extends BaseAction {

        /**
         * Instantiates a new Find item by description.
         */
        FindItemByDescription() {
            super("Find item by description.");
        };

        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) throws SQLException {
            String desc = input.ask("Please, enter the task`s description to find: ");

            Item[] result = tracker.findByDescription(desc);

            if (result.length != 0) {
                for (Item item : result) {
                    if (item != null) { System.out.println(item.getId()); }
                }
            } else {
                System.out.println("No matches. Enter the task`s description again");
            }
        }

    }

    private class FindItemByCreate extends BaseAction {

        /**
         * Instantiates a new Find item by create.
         */
        FindItemByCreate() {
            super("Find item by date.");
        };

        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) throws SQLException {
            long create = input.ask("Please, enter the task`s date to find: ", true);

            Item[] result = tracker.findByDate(create);

            if (result.length != 0) {
                for (Item item : result) {
                    if (item != null) { System.out.println(item.getId()); }
                }
            } else {
                System.out.println("No matches. Enter the task`s date again");
            }

        }

    }

    /**
     * The type Show items.
     */
    public static class ShowItems extends BaseAction {

        /**
         * Instantiates a new Show items.
         */
        ShowItems() {
            super("Show all items.");
        };

        public int key() {
            return 7;
        }

        public void execute(Input input, Tracker tracker) throws SQLException {

            Item[] result = tracker.getAll();

            if (result.length != 0) {
                for (Item item : result) {
                    if (item != null) { System.out.println(item.getId()); }
                }
            } else {
                System.out.println("Tracker is empty.");
            }
        }

    }

}