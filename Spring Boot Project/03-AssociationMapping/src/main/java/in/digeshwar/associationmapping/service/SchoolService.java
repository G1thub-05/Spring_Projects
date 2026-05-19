package in.digeshwar.associationmapping.service;


import in.digeshwar.associationmapping.entity.Student;
import in.digeshwar.associationmapping.entity.Teacher;
import in.digeshwar.associationmapping.repository.StudentRepository;
import in.digeshwar.associationmapping.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {

    @Autowired
    private TeacherRepository teacherRepo;

    @Autowired
    private StudentRepository studentRepo;

    // CREATE Teacher + Students
    public Teacher createTeacherWithStudents(Teacher teacher) {
        teacher.getStudents().forEach(s -> s.setTeacher(teacher));
        return teacherRepo.save(teacher); // cascade saves students too
    }

    // READ Teacher by ID
    public Teacher getTeacher(Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    // ADD Student to Teacher
    public Student addStudent(Long teacherId, Student student) {
        Teacher t = getTeacher(teacherId);
        t.addStudent(student);
        teacherRepo.save(t);
        return student;
    }

    // UPDATE Student
    public Student updateStudent(Long studentId, String name) {
        Student s = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        s.setFullName(name);
        return studentRepo.save(s);
    }

    // DELETE Student
    public void deleteStudent(Long studentId) {
        studentRepo.deleteById(studentId);
    }

    // DELETE Teacher (Cascade deletes Students automatically)
    public void deleteTeacher(Long teacherId) {
        teacherRepo.deleteById(teacherId);
    }
}

