package shestakov.services;

public class KeysValidator {
    public String pattern;
    public boolean stopAtFirstResult;

    public boolean parsedSuccessful(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];
            if (arg.equals("-f")) {
                try {
                    this.pattern = args[++i];
                } catch (ArrayIndexOutOfBoundsException aie) {
                    System.out.println("incorrect value of '-f' argument");
                    return false;
                }
            } else if (arg.equals("-s")) {
                this.stopAtFirstResult = true;
            }
        }
        return true;
    }
}
