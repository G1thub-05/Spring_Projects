package in.digeshwar.associationmapping.repository;

import in.digeshwar.associationmapping.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {}