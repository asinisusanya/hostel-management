package nazeem.web.service;

import java.util.Collections;
import java.util.List;

import nazeem.web.model.Hostel;
import nazeem.web.repository.IHostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HostelService {

    @Autowired
    private IHostelRepository repo;

    public List<Hostel> listAll() {
        return repo.findAll();
    }

    public Page<Hostel> findPaginated(String search, Pageable pageable) {
        List<Hostel> hostels = repo.searchHostel(search); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Hostel> list;

        if (hostels.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, hostels.size());
            list = hostels.subList(startItem, toIndex);
        }

        Page<Hostel> bookPage = new PageImpl<Hostel>(list, PageRequest.of(currentPage, pageSize), hostels.size());

        return bookPage;
    }


    public void save(Hostel hostel) {
        repo.save(hostel);
    }

    public Hostel get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}