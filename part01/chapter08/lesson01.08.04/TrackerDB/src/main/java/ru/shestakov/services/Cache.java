package ru.shestakov.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Cache {
    private static final Logger Log = LoggerFactory.getLogger(Cache.class);

    public static void main(String[] args) {
        /*User user = new User("test");
        WeakReference<User> weakUser = new WeakReference<User>(user);
        user = null;
        System.gc();
        System.out.println(user);
        System.out.println(weakUser.get());*/

        WeakReference<User> weakUser = new WeakReference<User>(new User("test"));
        SoftReference<User> softUser = new SoftReference<User>(new User("test"));
        System.gc();
        System.out.println(weakUser.get());
        System.out.println(softUser.get());
    }

}
