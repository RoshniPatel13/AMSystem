package htd.com.amsystem.database;

public class AttendanceDetails {
    int aId;
    int sId;
    String percentage;
    String status;
    int subId;
    String date;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getsId() {
        return sId;
    }

    public int getSubId() {
        return subId;
    }

    public int getaId() {
        return aId;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getStatus() {
        return status;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }
}
