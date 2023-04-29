package pe.idat.creacionescarrasco.model;

public class WorkPosition {

    private String _id;
    private String name;
    private String description;
    private String work_start_time;
    private String work_end_time;
    private boolean isActive;

    public WorkPosition(String _id, String name, String description, String work_start_time, String work_end_time, boolean isActive) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.work_start_time = work_start_time;
        this.work_end_time = work_end_time;
        this.isActive = isActive;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWork_start_time() {
        return work_start_time;
    }

    public void setWork_start_time(String work_start_time) {
        this.work_start_time = work_start_time;
    }

    public String getWork_end_time() {
        return work_end_time;
    }

    public void setWork_end_time(String work_end_time) {
        this.work_end_time = work_end_time;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
