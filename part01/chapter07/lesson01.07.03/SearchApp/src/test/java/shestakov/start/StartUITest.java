package shestakov.start;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import shestakov.db.KeysValidator;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class StartUITest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void whenSearchFilesUsesIOShouldFindCorrectResult() throws ExecutionException, InterruptedException {
        try {
            this.testFolder.newFile("temp-file-name1.tmp");
            this.testFolder.newFile("temp-file-name2.tmp");
            this.testFolder.newFile("temp-file-name3.tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] myArgs = new String[] {"-f", "tmp"};
        KeysValidator kv = new KeysValidator();
        SearchApp sa = new SearchApp(kv);
        sa.startSearching();
    }
}