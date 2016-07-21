package shestakov.start;

import shestakov.models.Key;
/*import shestakov.nio.FileFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;*/

public class SearchApp {
    private Key[] keys;

    public void setKeys(Key[] keys) {
        this.keys = keys;
    }

    public void startBigSearch() {

/*
//        Path startPath = Paths.get("C:\\test");
        Path startPath = Paths.get("");

        //Строка с glob-шаблоном
        String pattern = "glob:*.java";

        //Строка с regex-шаблоном
        //String pattern = "regex:\\S+\\.java";

        try {
            Files.walkFileTree(startPath, new FileFinder(pattern));
            System.out.println("File search completed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
*/


    }
}
