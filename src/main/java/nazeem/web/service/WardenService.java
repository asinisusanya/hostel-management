package nazeem.web.service;


import nazeem.web.model.Warden;
import nazeem.web.repository.IWardenRepository;
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
public class WardenService {

    @Autowired
    private IWardenRepository repo;

    public List<Warden> listAll() {
        return repo.findAll();
    }

    public Page<Warden> findPaginated(String search, Pageable pageable) {
        List<Warden> wardens = repo.searchWarden(search); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Warden> list;

        if (wardens.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, wardens.size());
            list = wardens.subList(startItem, toIndex);
        }

        Page<Warden> bookPage = new PageImpl<Warden>(list, PageRequest.of(currentPage, pageSize), wardens.size());

        return bookPage;
    }


    public void save(Warden warden) {
        repo.save(warden);
    }

    public Warden get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}