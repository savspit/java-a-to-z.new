package shestakov.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import shestakov.services.KeysValidator;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void whenSearchFilesUsesIOShouldFindCorrectResult() {
        try {
            this.testFolder.newFile("temp-file-name1.tmp");
            this.testFolder.newFile("temp-file-name2.tmp");
            this.testFolder.newFile("temp-file-name3.tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] myArgs = new String[] {"-f", "tmp", "-p", this.testFolder.getRoot().toString(), "-r", "io", "-t", "1", null};


        KeysValidator kv = new KeysValidator();
        SearchApp sa = new SearchApp(kv);
        sa.startSearching();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(sa.getFoundedFiles().size(), is(3));
    }

    @Test
    public void whenSearchFilesUsesIOAndNeedToBeStoppedAtFirstResultShouldReturnOnlyOneResult() {
        try {
            this.testFolder.newFile("temp-file-name1.tmp");
            this.testFolder.newFile("temp-file-name2.tmp");
            this.testFolder.newFile("temp-file-name3.tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] myArgs = new String[] {"-f", "tmp", "-p", this.testFolder.getRoot().toString(), "-r", "io", "-t", "1", "-s"};

        KeysValidator kv = new KeysValidator();
        SearchApp sa = new SearchApp(kv);
        sa.startSearching();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(sa.getFoundedFiles().size(), is(1));
    }

    @Test
    public void whenSearchFilesUsesNIOShouldFindCorrectResult() {
        try {
            this.testFolder.newFile("temp-file-name1.tmp");
            this.testFolder.newFile("temp-file-name2.tmp");
            this.testFolder.newFile("temp-file-name3.tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] myArgs = new String[] {"-f", "tmp", "-p", this.testFolder.getRoot().toString(), "-r", "nio", "-t", "1", null};

        KeysValidator kv = new KeysValidator();
        SearchApp sa = new SearchApp(kv);
        sa.startSearching();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(sa.getFoundedFiles().size(), is(3));
    }

}