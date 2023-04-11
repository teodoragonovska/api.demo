package mk.iwec.api.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.iwec.api.demo.domain.Student;
import mk.iwec.api.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;

	@Override
	public List<Student> findAll() {
		Iterable<Student> iterable = repository.findAll();
		List<Student> result = StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
		return result;
	}
	
	@Override
	public Student findById(Integer id) {
		Optional<Student> opt = repository.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public Student save(Student student) {
		return repository.save(student);
	}

	@Override
	public Student update(Student student) {

		Optional<Student> opt = repository.findById(student.getId());

		opt.ifPresentOrElse(s -> {
			s.setFirstName(student.getFirstName());
			s.setLastName(student.getLastName());
			save(s);
		}, null);

		return repository.findById(student.getId()).get();

	}

	@Override
	public boolean delete(Integer id) {
		boolean exists = repository.existsById(id);
		if (exists) {
			repository.deleteById(id);
		}
		return exists;
	}

}
