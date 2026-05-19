package in.digeshwar.service;

import in.digeshwar.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import in.digeshwar.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Iterable<Student> SaveAllStudent() {
        return repo.saveAll(List.of(
                new Student ("Digeshwar", 22),
                new Student ("Michael", 23),
                new Student ("Daniel", 24),
                new Student ("john", 25)
        ));
    }

    public void SortAllStudentByNameDesc() {
        Sort sort = Sort.by( "name").descending();
        List<Student> list = repo.findAll(sort);
        list.forEach(System.out::println);
    }

    public void GetStudentById(Integer id) {
        Student student = repo.findById(id)
                .orElse(null);
        if (student != null) {
            System.out.println(student);
        }
        else { System.out.println("Student not found with ID " + id);}
    }

    public List<Student> SearchStudentByCustome(String name , Integer id) {
       return repo.findByNameAndAgeGreaterThan(name, id);
    }

    public void DeleteStudentById(Integer id) {
        repo.deleteStudentById(id);
    }

    public void UpdateStudentNameById(Integer id, String name) {
        repo.updateStudentName(id, name);
    }

    public void GetAllStudentList() {
        List<Student> lst = repo.findAll();
        lst.forEach(System.out::println);
    }

    public void GetAllStudentListWithPaginationAndSort() {

        Integer pageNum = 1;   // pageNumber (zero-based index) →  we will get from UI
        Integer pageSize = 3;  // it is fixed (Per page 3 records show)

        PageRequest pageReq = PageRequest.of(pageNum - 1, pageSize, Sort.by("name").descending());
        Slice<Student> page = repo.findAll(pageReq);
        List<Student> std = page.getContent();
        std.forEach(System.out::println);
    }
}
