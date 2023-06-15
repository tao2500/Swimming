package pojo;

public class activity {
    private int id;
    private String name;
    private String messige;
    private String startime;
    private String overtime;
    private String imgsrc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessige() {
        return messige;
    }

    public void setMessige(String messige) {
        this.messige = messige;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    @Override
    public String toString() {
        return "activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", messige='" + messige + '\'' +
                ", startime='" + startime + '\'' +
                ", overtime='" + overtime + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                '}';
    }
}
