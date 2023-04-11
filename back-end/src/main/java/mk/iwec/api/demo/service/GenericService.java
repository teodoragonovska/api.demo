package mk.iwec.api.demo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface GenericService<T> {

	public List<T> findAll();

	public T findById(Integer id);

	public T save(T t);

	public T update(T t);

	public boolean delete(Integer id);

}
