package ru.shestakov.start;

import ru.shestakov.models.*;
import ru.shestakov.templates.*;

/**
 * Creates new menu action
 */
class UpdateItem extends BaseAction {

    /**
     * Initializes a newly created menu action
     */
    UpdateItem() {
        super("Edit the new item.");
    };

    /**
     * Set action`s key
     * @return
     */
    public int key() {
        return 1;
    }

    /**
     * Executes user`s action
     * @param input
     * @param tracker
     */
    public void execute(Input input, Tracker tracker) {
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
 * Creates new menu action
 */
public class MenuTracker {

    /** User`s input */
    private Input input;
    /** Tracker */
    private Tracker tracker;
    /** Action`s array */
    protected UserAction[] actions;
    /** Position of action`s array */
    private int position = 0;

    /**
     * Initializes a newly created menu action
     * @param input
     * @param tracker
     */
    public MenuTracker(Input input, Tracker tracker, int size) {
        this.input = input;
        this.tracker = tracker;
        this.actions = new UserAction[size];
    }

    /**
     * Fills the menu actions
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
     * Add the new action in menu
     * @param action
     */
    public void addAction(UserAction action) {
        this.actions[position++] = action;
    }

    public int[] getRanges() {
        int[] ranges = new int[this.actions.length];
        for (int index=0; index<this.actions.length; index++) {
            ranges[index] = index;
        }
        return ranges;
    }

    /**
     * Execute selected menu
     * @param key
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Shows actions info
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) { this.showItem(action); }
        }
    }

    public void showItem(UserAction action) {
        System.out.println(action.info());
    }

    /**
     * Creates new menu action
     */
    private class AddItem extends BaseAction {

        /**
         * Initializes a newly created menu action
         */
        AddItem() {
            super("Add the new item.");
        };

        /**
         * Set action`s key
         * @return
         */
        public int key() {
            return 0;
        }

        /**
         * Executes user`s action
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the task`s name: ");
            String desc = input.ask("Please, enter the task`s desc: ");
            long create = input.ask("Please, enter the task`s date: ", true);

            tracker.add(new Task(name, desc, create));
        }

    }

    /**
     * Creates new menu action
     */
    private class DeleteItem extends BaseAction {

        /**
         * Initializes a newly created menu action
         */
        DeleteItem() {
            super("Delete the item.");
        };

        /**
         * Set action`s key
         * @return
         */
        public int key() {
            return 2;
        }

        /**
         * Executes user`s action
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
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

    /**
     * Creates new menu action
     */
    private class AddCommentToItem extends BaseAction {

        /**
         * Initializes a newly created menu action
         */
        AddCommentToItem() {
            super("Add comment to the item.");
        };

        /**
         * Set action`s key
         * @return
         */
        public int key() {
            return 3;
        }

        /**
         * Executes user`s action
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
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

    /**
     * Creates new menu action
     */
    private class FindItemByName extends BaseAction {

        /**
         * Initializes a newly created menu action
         */
        FindItemByName() {
            super("Find item by name.");
        };

        /**
         * Set action`s key
         * @return
         */
        public int key() {
            return 4;
        }

        /**
         * Executes user`s action
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the task`s name to find: ");

            Filter filter = new FilterByName(name);

            Item[] result = tracker.findBy(filter);

            if (result.length != 0) {
                for (Item item : result) {
                    if (item != null) { System.out.println(item.getId()); }
                }
            } else {
                System.out.println("No matches. Enter the task`s name again");
            }
        }

    }

    /**
     * Creates new menu action
     */
    private class FindItemByDescription extends BaseAction {

        /**
         * Initializes a newly created menu action
         */
        FindItemByDescription() {
            super("Find item by description.");
        };

        /**
         * Set action`s key
         * @return
         */
        public int key() {
            return 5;
        }

        /**
         * Executes user`s action
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            String desc = input.ask("Please, enter the task`s description to find: ");

            Filter filter = new FilterByDescription(desc);

            Item[] result = tracker.findBy(filter);

            if (result.length != 0) {
                for (Item item : result) {
                    if (item != null) { System.out.println(item.getId()); }
                }
            } else {
                System.out.println("No matches. Enter the task`s description again");
            }
        }

    }

    /**
     * Creates new menu action
     */
    private class FindItemByCreate extends BaseAction {

        /**
         * Initializes a newly created menu action
         */
        FindItemByCreate() {
            super("Find item by date.");
        };

        /**
         * Set action`s key
         * @return
         */
        public int key() {
            return 6;
        }

        /**
         * Executes user`s action
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            long create = input.ask("Please, enter the task`s date to find: ", true);

            Filter filter = new FilterByCreate(create);

            Item[] result = tracker.findBy(filter);

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
     * Creates new menu action
     */
    public static class ShowItems extends BaseAction {

        /**
         * Initializes a newly created menu action
         */
        ShowItems() {
            super("Show all items.");
        };

        /**
         * Set action`s key
         * @return
         */
        public int key() {
            return 7;
        }

        /**
         * Executes user`s action
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {

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