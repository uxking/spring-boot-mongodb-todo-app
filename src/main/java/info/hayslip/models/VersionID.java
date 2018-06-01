package info.hayslip.models;

public class VersionID {

    private int version;
    private String message;

    public VersionID() {
        this.version = 1;
        this.message = "Todo App";
    }

    public int getVersion() {
        return version;
    }


    public String getMessage() {
        return message;
    }

}
