package pe.idat.creacionescarrasco.model;

public class WorkPositionRequest {
    private String name;
    private String description;
    private String work_start_time;
    private String work_end_time;

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
}
