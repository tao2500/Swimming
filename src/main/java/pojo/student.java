package pojo;

public class student {
    private int id;
    private String password;
    private String crowd;
    private String Gender;
    private String name;
    private String telephone;
    private String item;
    private String member;
    private String classtype;
    private String overtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getClasstype() {
        return classtype;
    }

    public void setClasstype(String classtype) {
        this.classtype = classtype;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", crowd='" + crowd + '\'' +
                ", Gender='" + Gender + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", item='" + item + '\'' +
                ", member='" + member + '\'' +
                ", classType='" + classtype + '\'' +
                ", overtime='" + overtime + '\'' +
                '}';
    }
}
