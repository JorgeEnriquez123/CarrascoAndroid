package pe.idat.creacionescarrasco.model.registro;

public class AsistenciasUser {
    private String _id;
    private String date;
    private String start_time;
    private String lunch_start_time;
    private String lunch_end_time;
    private String end_time;
    private boolean time_fulfilled;
    private int missing_minutes;
    private boolean isWithinWorkingHour;
    private String user;
    private boolean isActive;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getLunch_start_time() {
        return lunch_start_time;
    }

    public void setLunch_start_time(String lunch_start_time) {
        this.lunch_start_time = lunch_start_time;
    }

    public String getLunch_end_time() {
        return lunch_end_time;
    }

    public void setLunch_end_time(String lunch_end_time) {
        this.lunch_end_time = lunch_end_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public boolean isTime_fulfilled() {
        return time_fulfilled;
    }

    public void setTime_fulfilled(boolean time_fulfilled) {
        this.time_fulfilled = time_fulfilled;
    }

    public int getMissing_minutes() {
        return missing_minutes;
    }

    public void setMissing_minutes(int missing_minutes) {
        this.missing_minutes = missing_minutes;
    }

    public boolean isWithinWorkingHour() {
        return isWithinWorkingHour;
    }

    public void setWithinWorkingHour(boolean withinWorkingHour) {
        isWithinWorkingHour = withinWorkingHour;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
