package shestakov.services;

import shestakov.models.Buffer;
import shestakov.models.Key;

public class KeysValidator {
    private Key[] keys;

    public Key[] getKeys() {
        return keys;
    }

    public boolean parsedSuccessful(String[] args) {
        this.keys = new Key[args.length];
        while (true) {
            if (args.length == 0 || args.length == 1 || args.length > 5) {
                new Buffer().show("incorrect arguments. please try again or run only with '-f' argument");
                return false;
            }
            for (int i=0; i<args.length; i++) {
                if (args[i] != null && args[i].startsWith("-f")) {
                    if (i+1 <= args.length && args[i+1] != null && !args[i+1].equals(" ") && !args[i+1].startsWith("-")) {
                        addKey("f", args[i++]);
                    } else { new Buffer().show("incorrect value of '-f' argument"); return false; }
                }
                if (args[i] != null && args[i].startsWith("-p")) {
                    if (i+1 <= args.length && args[i+1] != null && !args[i+1].equals(" ") && !args[i+1].startsWith("-")) {
                        addKey("p", args[i++]);
                    } else { new Buffer().show("incorrect value of '-p' argument"); return false; }
                }
                if (args[i] != null && args[i].startsWith("-s")) {
                    if ((i+1 == args.length) || args[i++].startsWith("-")) {
                        addKey("s", args[i++]);
                    } else { new Buffer().show("incorrect value of '-s' argument"); return false; }
                }
            }
            return true;
        }
    }

    public void addKey(String key, String value) {
        boolean founded = false;
        for (int j=0; j<this.keys.length; j++) {
            if (this.keys[j] != null && key.equals(this.keys[j].getKey())) {
                this.keys[j].setValue(value);
                founded = true;
                break;
            }
        }
        if (!founded) {
            for (int k=0; k< this.keys.length; k++) {
                if (this.keys[k] == null) {
                    this.keys[k] = new Key(key,value);
                    break;
                }
            }
        }
    }
}
