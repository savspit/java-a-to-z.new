package shestakov.start;

import org.junit.Assert;
import org.junit.Test;
import shestakov.models.CacheManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartUITest {

    @Test
    public void whenGCisDoneShouldKeepSoftReference() throws IOException, InterruptedException {
        CacheManager cm = new CacheManager();
        String key = "UseSerialGC.txt";
        cm.checkKey(key);
        Assert.assertNotNull(cm.getValue(key).get());
        for (int i=0; i<3; i++) {
            System.gc();
            Thread.sleep(3000);
            cm.getValue(key).get();
        }
        Assert.assertNotNull(cm.getValue(key).get());
    }

    @Test
    public void whenOutOfMemoryGCshouldClearSoftReference() throws IOException, InterruptedException {
        CacheManager cm = new CacheManager();
        String key = "UseSerialGC.txt";
        cm.checkKey(key);
        Assert.assertNotNull(cm.getValue(key).get());
        try
        {
            List bigArr = new ArrayList();
            while(true) {
                bigArr.add(new byte[1024 * 1024 * 10]);
            }
        }
        catch (OutOfMemoryError e) {
            Thread.sleep(3000);
            Assert.assertNull(cm.getValue(key).get());
        }
    }

}