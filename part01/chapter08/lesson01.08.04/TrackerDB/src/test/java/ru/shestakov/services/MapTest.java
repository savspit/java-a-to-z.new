package ru.shestakov.services;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapTest {

    public static final class User {
        public String name;
        public int children;
        Calendar birthday;

        public User(String name) {
            this.name = name;
        }

        public User(String name, int children, Calendar birthday) {
            this.name = name;
            this.children = children;
            this.birthday = birthday;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + children;
            result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!(o instanceof User)) return false;

            User user = (User) o;

            return name.equals(user.name) &&
                    children == user.children &&
                    birthday.equals(user.birthday);
        }

    }

    @Test
    public void testSet() {

        Calendar cal = Calendar.getInstance();

        User first = new User("Petr", 1, cal);
        User second = new User("Petr", 1, cal);

        Set<User> set = new HashSet<>();
        set.add(first);
        set.add(second);

        System.out.println(set);
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println(first.equals(second));

        assertThat(set.size(), is(2));
    }

    @Test
    public void testMap() {

        Calendar cal = Calendar.getInstance();

        User first = new User("Petr", 1, cal);
        User second = new User("Petr", 1, cal);

        Map<User, String> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");

        System.out.println(map);
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println(first.equals(second));

        assertThat(map.size(), is(2));
    }

}
