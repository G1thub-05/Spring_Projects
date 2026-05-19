package in.digeshwar;

import in.digeshwar.entity.Student;
import in.digeshwar.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.List;

@SpringBootApplication
public class MySpringBootAppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MySpringBootAppApplication.class, args);
        System.out.println("🚀 Spring Boot Application Started Successfully!");
        StudentService stdservice = context.getBean(StudentService.class);
        stdservice.SaveAllStudent();
        System.out.println("🚀 Get All Student Sort By Name in Desc Order... Before Delete");
        stdservice.SortAllStudentByNameDesc();

        System.out.println("🚀 Get Student By ID=3");
        stdservice.GetStudentById(3);

        System.out.println("🚀 Get Record Where Name = Digeshwar AND Age > 21 ");
        List<Student> lst = stdservice.SearchStudentByCustome("Digeshwar", 21);
        System.out.println(lst);

        stdservice.DeleteStudentById(3);
        stdservice.UpdateStudentNameById(1, "Mr. Digeshwar");
        System.out.println(" 🚀 Get All Student List After Delete & Update Student by ID");
        stdservice.GetAllStudentList();

        System.out.println("📄 Pagination and Sorting Student by Name in Desc Order");
        stdservice.GetAllStudentListWithPaginationAndSort();
    }
}
