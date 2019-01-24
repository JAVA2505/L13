import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Company {
    private int id;
    private String director;
    private String name;
    private List<Department> departments = new LinkedList<>();

    public Company() {
    }

    public Company(int id, String director, String name, List<Department> departments) {
        this.id = id;
        this.director = director;
        this.name = name;
        this.departments = departments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
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
