package mk.iwec.api.demo.repository;

import org.springframework.data.repository.CrudRepository;

import mk.iwec.api.demo.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}