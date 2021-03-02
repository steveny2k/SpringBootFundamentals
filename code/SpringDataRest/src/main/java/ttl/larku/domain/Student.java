package ttl.larku.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    public enum Status {
        FULL_TIME, PART_TIME, HIBERNATING
    }

    ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

//    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Status status = Status.FULL_TIME;

    @JacksonXmlElementWrapper(localName = "classes")
    @JacksonXmlProperty(localName = "class")
    @ManyToMany (fetch = FetchType.EAGER )
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Student_Scheduledclass",
            joinColumns = @JoinColumn(name = "students_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "classes_id", referencedColumnName = "id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"students_id", "classes_id"})})
    private List<ScheduledClass> classes;

    public Student() {
        this("Unknown");
    }

    public Student(String name) {
        this(name, null, Status.FULL_TIME, new ArrayList<ScheduledClass>());
    }

    public Student(String name, String phoneNumber, Status status) {
        this(name, phoneNumber, status, new ArrayList<ScheduledClass>());
    }

    public Student(String name, String phoneNumber, Status status, List<ScheduledClass> classes) {
        super();
        this.name = name;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.classes = classes;
    }

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

    @JsonIgnore
    public Status[] getStatusList() {
        return Status.values();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public List<ScheduledClass> getClasses() {
        return classes;
    }

    public void setClasses(List<ScheduledClass> classes) {
        this.classes = classes;
    }

    public void addClass(ScheduledClass sClass) {
        classes.add(sClass);
    }

    public void dropClass(ScheduledClass sClass) {
        classes.remove(sClass);
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", status=" + status
                + ", classes=" + classes + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((classes == null) ? 0 : classes.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (classes == null) {
            if (other.classes != null)
                return false;
        } else if (!classes.equals(other.classes))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @PrePersist
    public void prePersist() {
        System.out.println("In Student::PrePersist: " + this.toString());
    }

    @PostPersist
    public void afterPersisting() {
        System.out.println("In Student::PostPersist: " + this.toString());
    }

    @PreRemove
    public void beforeRemoving() {
        System.out.println("In Student::PreRemove");
    }

    @PostRemove
    public void afterRemoving() {
        System.out.println("In Student::PostRemove");
    }

    @PreUpdate
    public void beforeUpdate() {
        System.out.println("In Student::PreUpdate");
    }

    @PostUpdate
    public void afterUpdate() {
        System.out.println("In Student::PostUpdate");
    }

    @PostLoad
    public void beforeLoading() {
        System.out.println("In Student::PostLoad");
    }
}
