package ru.shestakov;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class CheckNullTest {

    @Test
    public void whenExistNullElementsShouldRemoveIt() {
        int[] arr = {0, 1, 2, 3, 4, 5};
        CheckNull checkNull = new CheckNull();
        int[] newArr = checkNull.clear(arr);
        Assert.assertThat(newArr.length, is(arr.length-1));
    }

}