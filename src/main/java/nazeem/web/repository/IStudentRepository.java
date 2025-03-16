package nazeem.web.repository;
import nazeem.web.model.Hostel;
import nazeem.web.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    @Query("select p from Student p " +
            //"join p.productType pt " +
            "where 1=1" +
            "and ( upper(p.surname) like concat('%', upper(?1), '%') " +
            "       or upper(p.hostelId) like concat('%', upper(?1), '%') " +
            "       or upper(p.roomId) like concat('%', upper(?1), '%')" +
            //"       or upper(pt.name) like concat('%', upper(?1), '%')" +
            ")")
    List<Student> searchStudent(String criteria);

//    @Query("SELECT s FROM Student s WHERE s.active = true")
//    List<Student> findAllActive();


}