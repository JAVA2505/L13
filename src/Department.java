import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Department {
    private int id;
    private String name;
    private List<Employee> emplyees = new LinkedList<>();

    public Department() {
    }

    public Department(int id, String name, List<Employee> emplyees) {
        this.id = id;
        this.name = name;
        this.emplyees = emplyees;
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

    public List<Employee> getEmplyees() {
        return emplyees;
    }

    public void setEmplyees(List<Employee> emplyees) {
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
