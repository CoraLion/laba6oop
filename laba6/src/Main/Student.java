package Main;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Student implements Serializable {

    private int id;
    private String secname;
    private String name;
    private String threename;
    private LocalDate age;
    private String address;
    private int mob;
    private String fac;
    private int curs;
    private int group;

    public Student(int id, String secname, String name, String threename, LocalDate age, String address, int mob, String fac, int curs, int group) {
        this.id = id;
        this.secname = secname;
        this.name = name;
        this.threename = threename;
        this.age = age;
        this.address = address;
        this.mob = mob;
        this.fac = fac;
        this.curs = curs;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecname() {
        return secname;
    }

    public void setSecname(String secname) {
        this.secname = secname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThreename() {
        return threename;
    }

    public void setThreename(String threename) {
        this.threename = threename;
    }

    public LocalDate getAge() {
        return age;
    }

    public void setAge(LocalDate age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMob() {
        return mob;
    }

    public void setMob(int mob) {
        this.mob = mob;
    }

    public String getFac() {
        return fac;
    }

    public void setFac(String fac) {
        this.fac = fac;
    }

    public int getCurs() {
        return curs;
    }

    public void setCurs(int curs) {
        this.curs = curs;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                mob == student.mob &&
                curs == student.curs &&
                group == student.group &&
                secname.equals(student.secname) &&
                name.equals(student.name) &&
                threename.equals(student.threename) &&
                Objects.equals(age, student.age) &&
                Objects.equals(address, student.address) &&
                Objects.equals(fac, student.fac);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, secname, name, threename, age, address, mob, fac, curs, group);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", secname='" + secname + '\'' +
                ", name='" + name + '\'' +
                ", threename='" + threename + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", mob=" + mob +
                ", fac='" + fac + '\'' +
                ", curs=" + curs +
                ", group=" + group +
                '}';
    }
}
