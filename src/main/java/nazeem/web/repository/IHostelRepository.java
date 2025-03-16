package nazeem.web.repository;
import nazeem.web.model.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHostelRepository extends JpaRepository<Hostel, Long> {
    @Query("select p from Hostel p " +
            //"join p.productType pt " +
            "where 1=1" +
            "and ( upper(p.name) like concat('%', upper(?1), '%') " +
            "       or upper(p.location) like concat('%', upper(?1), '%') " +
            "       or upper(p.hostelType) like concat('%', upper(?1), '%')" +
            //"       or upper(pt.name) like concat('%', upper(?1), '%')" +
            ")")
    List<Hostel> searchHostel(String criteria);
}