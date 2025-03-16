package nazeem.web.repository;

import nazeem.web.model.Warden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IWardenRepository extends JpaRepository<Warden, Long> {
    @Query("select p from Warden p " +
            //"join p.productType pt " +
            "where 1=1" +
            "and ( upper(p.name) like concat('%', upper(?1), '%') " +
            "       or upper(p.hostelId) like concat('%', upper(?1), '%') " +
//            "       or upper(p.room_id) like concat('%', upper(?1), '%')" +
            //"       or upper(pt.name) like concat('%', upper(?1), '%')" +
            ")")
    List<Warden> searchWarden(String criteria);
}