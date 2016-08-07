package shestakov.services;

/**
 * The type Keys validator.
 */
public class KeysValidator {
    /**
     * The Pattern.
     */
    public String pattern;
    /**
     * The Stop at first result.
     */
    public boolean stopAtFirstResult;

    /**
     * Parsed successful boolean.
     *
     * @param args the args
     * @return the boolean
     */
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
