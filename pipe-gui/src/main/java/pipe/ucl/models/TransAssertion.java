package pipe.ucl.models;

public class TransAssertion {
    private String startStateId;
    private String startStateName;
    private String startStateDate;
    private String endStateId;
    private String endStateName;
    private String endStateDate;
    private String author;
    private String time;
    private String action;
    private Boolean Sign;

    public String getStartStateName() {
        return startStateName;
    }

    public void setStartStateName(String startStateName) {
        this.startStateName = startStateName;
    }

    public String getStartStateDate() {
        return startStateDate;
    }

    public void setStartStateDate(String startStateDate) {
        this.startStateDate = startStateDate;
    }

    public String getEndStateName() {
        return endStateName;
    }

    public void setEndStateName(String endStateName) {
        this.endStateName = endStateName;
    }

    public String getEndStateDate() {
        return endStateDate;
    }

    public void setEndStateDate(String endStateDate) {
        this.endStateDate = endStateDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Boolean getSign() {
        return Sign;
    }

    public void setSign(Boolean sign) {
        Sign = sign;
    }

    public void setStartStateId(String startStateId) {
        this.startStateId = startStateId;
    }

    public String getStartStateId() {
        return startStateId;
    }

    public String getEndStateId() {
        return endStateId;
    }

    public void setEndStateId(String endStateId) {
        this.endStateId = endStateId;
    }
}
