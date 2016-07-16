package ru.shestakov.start;

import ru.shestakov.models.*;

public class StartUI {

    public static void main(String[] args) {

        Tracker tracker = new Tracker();

        // add

        Task task1 = new Task("first task", "first desc", System.nanoTime());
        tracker.add(task1);

        Task task2 = new Task("second task", "second desc", System.nanoTime());
        tracker.add(task2);

        Task task3 = new Task("third task", "third desc", System.nanoTime());
        tracker.add(task3);

        Task task4 = new Task("forth task", "forth desc", System.nanoTime());
        tracker.add(task4);

        // update

        Task task5 = new Task("fifth task", "fifth desc", System.nanoTime());
        task5.setId(task2.getId());
        tracker.update(task5);

        // delete

        tracker.delete(task1);

        // find by name

        Task task6 = new Task("sixth task", "sixth desc", System.nanoTime());
        tracker.add(task6);
        Filter filter1 = new FilterByName(task6.getName());

        System.out.println("founded task by name is: " + tracker.findBy(filter1));

        // find by description

        Task task7 = new Task("seventh task", "seventh desc", System.nanoTime());
        tracker.add(task7);
        Filter filter2 = new FilterByDescription(task7.getDescription());

        System.out.println("founded task by description is: " + tracker.findBy(filter2));

        // find by create
        Task task8 = new Task("eighth task", "eighth desc", 271545279656360L);
        tracker.add(task8);
        Filter filter3 = new FilterByDescription(task8.getDescription());

        System.out.println("founded task by create is: " + tracker.findBy(filter3));

        // add comment

        Comment comment = new Comment(task3.getId(), "Some kind of comment");
        tracker.addComment(comment);
        for (Comment nextComment : task3.getComments()) {
            if(nextComment != null) {
                System.out.println("task comment is: " + nextComment);
            }
        }

        // print all

        for (Item item : tracker.getAll()) {
            if(item != null) {
                System.out.println(item.getName());
            }
        }

    }

}