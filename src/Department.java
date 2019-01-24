import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Department {
    @XmlAttribute
    public int id;
    @XmlAttribute
    public String name;
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    public List<Employee> emplyees = new LinkedList<>();

    public Department() {
    }

    public Department(int id, String name, List<Employee> emplyees) {
        this.id = id;
        this.name = name;
        this.emplyees = emplyees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emplyees=" + emplyees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(emplyees, that.emplyees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, emplyees);
    }
}
