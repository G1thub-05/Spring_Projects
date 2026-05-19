

package in.digeshwar.associationmapping;


import in.digeshwar.associationmapping.entity.Student;
import in.digeshwar.associationmapping.entity.Teacher;
import in.digeshwar.associationmapping.service.SchoolService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
    public class AssociationMappingApplication {

        public static void main(String[] args) {
            SpringApplication.run(AssociationMappingApplication.class, args);
        }

        @Bean
        CommandLineRunner run(SchoolService service) {
            return args -> {

                System.out.println("=== PRACTICE CRUD START ===");


                // 1️⃣ CREATE teacher with students

                Teacher t = new Teacher();
                t.setName("Mr. John");

                Student s1 = new Student();
                s1.setFullName("Alice");

                Student s2 = new Student();
                s2.setFullName("Bob");

                t.addStudent(s1);
                t.addStudent(s2);

                Teacher savedTeacher = service.createTeacherWithStudents(t);
                System.out.println("Teacher Saved ID = " + savedTeacher.getId());

                // -------------------------------
                // 2️⃣ READ teacher
                // -------------------------------
                Teacher fetched = service.getTeacher(savedTeacher.getId());
                System.out.println("Fetched Teacher: " + fetched.getName());
                System.out.println("Students Count: " + fetched.getStudents().size());

                // -------------------------------
                // 3️⃣ UPDATE student
                // -------------------------------
                Long studentIdToUpdate = fetched.getStudents().get(0).getId();
                service.updateStudent(studentIdToUpdate, "Alice Updated");

                System.out.println("Student Updated: " + studentIdToUpdate);

                // -------------------------------
                // 4️⃣ DELETE student
                // -------------------------------
                Long studentIdToDelete = fetched.getStudents().get(1).getId();
                service.deleteStudent(studentIdToDelete);

                System.out.println("Student Deleted: " + studentIdToDelete);

                // -------------------------------
                // 5️⃣ DELETE teacher (cascade deletes remaining students)
                // -------------------------------
                service.deleteTeacher(savedTeacher.getId());
                System.out.println("Teacher Deleted with Cascade");

                System.out.println("=== PRACTICE CRUD END ===");
            };
        }
    }