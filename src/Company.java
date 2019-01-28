import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Company {
    @XmlAttribute
    public int id;
    @XmlElement
    public String director;
    @XmlAttribute
    public String name;
    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    public List<Department> departments = new LinkedList<>();

    public Company() {
    }

    public Company(int id, String director, String name, List<Department> departments) {
        this.id = id;
        this.director = director;
        this.name = name;
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", director='" + director + '\'' +
                ", name='" + name + '\'' +
                ", departments=" + departments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id &&
                Objects.equals(director, company.director) &&
                Objects.equals(name, company.name) &&
                Objects.equals(departments, company.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, director, name, departments);
    }
}
