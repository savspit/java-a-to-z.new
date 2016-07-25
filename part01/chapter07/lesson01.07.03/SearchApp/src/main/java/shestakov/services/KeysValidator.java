package shestakov.services;

import shestakov.models.Buffer;
import shestakov.models.Key;

public class KeysValidator {
    public String pattern;
    public String path;
    public ReaderEnum reader;
    public int numberOfThreads;
    public boolean stopAtFirstResult;

    public boolean parsedSuccessful(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            if (arg.equals("-f")) {
                try {
                    this.pattern = args[++i];
                } catch (ArrayIndexOutOfBoundsException aibe) {
                    new Buffer().show("incorrect value of '-f' argument");
                    return false;
                }
            } else if (arg.equals("-p")) {
                try {
                    this.path = args[++i];
                } catch (ArrayIndexOutOfBoundsException aibe) {
                    new Buffer().show("incorrect value of '-p' argument");
                    return false;
                }
            } else if (arg.equals("-r")) {
                try {
                    this.reader = (args[++i] == "io" ? reader.IO : reader.NIO);
                } catch (ArrayIndexOutOfBoundsException aibe) {
                    new Buffer().show("incorrect value of '-r' argument");
                    return false;
                }
            } else if (arg.equals("-t")) {
                try {
                    this.numberOfThreads = Integer.parseInt(args[++i]);
                } catch (ArrayIndexOutOfBoundsException aibe) {
                    new Buffer().show("incorrect value of '-t' argument");
                    return false;
                }
            } else if (arg.equals("-s")) {
                this.stopAtFirstResult = true;
            }
        }
        return true;
    }
}
