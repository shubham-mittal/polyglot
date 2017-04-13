package in.thegeekybaniya.polyglot.POJO;

/**
 * Created by Kabir on 13/04/2017.
 */

public class Translation {

    String orig, trans;

    public Translation(String orig, String trans) {
        this.orig = orig;
        this.trans = trans;
    }

    public Translation() {

    }

    public String getOrig() {

        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }
}
