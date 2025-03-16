package nazeem.web.service;



import nazeem.web.model.Student;
import nazeem.web.repository.IStudentRepository;
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
public class StudentService {

    @Autowired
    private IStudentRepository repo;

    public List<Student> listAll() {
        return repo.findAll();
    }

    public Page<Student> findPaginated(String search, Pageable pageable) {
        List<Student> students = repo.searchStudent(search); //repo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Student> list;

        if (students.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, students.size());
            list = students.subList(startItem, toIndex);
        }

        Page<Student> bookPage = new PageImpl<Student>(list, PageRequest.of(currentPage, pageSize), students.size());

        return bookPage;
    }


    public void save(Student student) {
        repo.save(student);
    }

    public Student get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}