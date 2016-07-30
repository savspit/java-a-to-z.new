package shestakov.services;

public class KeysValidator {
    public String pattern;
    public ReaderEnum reader;
    public int numberOfThreads;
    public boolean stopAtFirstResult;

    public boolean parsedSuccessful(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            if (arg.equals("-f")) {
                try {
                    this.pattern = args[++i];
                } catch (ArrayIndexOutOfBoundsException aie) {
                    new Console().show("incorrect value of '-f' argument");
                    return false;
                }
            } else if (arg.equals("-t")) {
                try {
                    this.numberOfThreads = Integer.parseInt(args[++i]);
                } catch (ArrayIndexOutOfBoundsException aie) {
                    new Console().show("incorrect value of '-t' argument");
                    return false;
                }
            } else if (arg.equals("-s")) {
                this.stopAtFirstResult = true;
            }
        }
        return true;
    }
}
