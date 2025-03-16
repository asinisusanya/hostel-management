package nazeem.web.service;


import nazeem.web.model.Room;
import nazeem.web.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class RoomService {

    @Autowired
    private IRoomRepository repo;

    public List<Room> listAll() {
        return repo.findAll();
    }

    public Page<Room> findPaginated(String search, Pageable pageable) {
        List<Room> rooms = repo.searchRoom(search); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Room> list;

        if (rooms.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, rooms.size());
            list = rooms.subList(startItem, toIndex);
        }

        Page<Room> bookPage = new PageImpl<Room>(list, PageRequest.of(currentPage, pageSize), rooms.size());

        return bookPage;
    }


    public void save(Room room) {
        repo.save(room);
    }

    public Room get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}