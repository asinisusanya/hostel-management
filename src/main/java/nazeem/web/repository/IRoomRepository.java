package nazeem.web.repository;
import nazeem.web.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRoomRepository extends JpaRepository<Room, Long> {
    @Query("select p from Room p " +
            //"join p.productType pt " +
            "where 1=1" +
            "and ( upper(p.hostel_id) like concat('%', upper(?1), '%') " +
            "       or upper(p.capacity) like concat('%', upper(?1), '%') " +
            //"       or upper(pt.name) like concat('%', upper(?1), '%')" +
            ")")
    List<Room> searchRoom(String criteria);
}