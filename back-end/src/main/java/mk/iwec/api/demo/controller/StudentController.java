package mk.iwec.api.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mk.iwec.api.demo.domain.Student;
import mk.iwec.api.demo.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v2/students")
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping
	@ResponseBody
	public List<Student> findAll() {
		return service.findAll();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Student> getById(@PathVariable(value = "id") Integer id) {
		Student student = service.findById(id);
		HttpStatus status = student != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Student>(student, status);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save(@RequestBody Student student) {
		return service.save(student).getId();
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Integer update(@RequestBody Student student) {
		return service.update(student).getId();
	}

	@DeleteMapping(value = "/{id}")
	public HttpStatus delete(@PathVariable(value = "id") Integer id) {
		boolean isDeleted = service.delete(id);
		return isDeleted ? HttpStatus.OK : HttpStatus.NOT_FOUND;
	}
}
