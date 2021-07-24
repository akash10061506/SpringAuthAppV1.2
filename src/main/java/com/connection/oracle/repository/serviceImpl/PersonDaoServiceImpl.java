package com.connection.oracle.repository.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connection.oracle.entity.Person;
import com.connection.oracle.exception.EmptyFieldException;
import com.connection.oracle.repository.service.PersonSpringDataRepository;

@Service
public class PersonDaoServiceImpl {
	@Autowired
	PersonSpringDataRepository repository;

//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		if (userName.equals("Akash")) {
//			return new User("Akash", "Akash123", new ArrayList<>()); // This arraylist is for rolls and premission
//		} else {
//			throw new UsernameNotFoundException("user not found !!");
//		}
//
//		// ese call karke authection details load hoti hai
//	}

	public List<Person> getAllEmployees() {
		List<Person> list = null;
		list = repository.findAll();

		return list;
	}

	public Person getId(int id) {

		Optional<Person> p = repository.findById(id);
		return p.get();

	}

	public Person createOrUpdateEmployee(Person entity) {

		Optional<Person> employee = repository.findById(entity.getId());

		if (employee.isPresent()) {
			Person newEntity = employee.get();
			newEntity.setId(entity.getId());
			newEntity.setName(entity.getName());
			newEntity.setLocation(entity.getLocation());
			newEntity.setSalary(entity.getSalary());
			newEntity = repository.save(newEntity);

			return newEntity;
		} else if (entity.getName().isEmpty() || entity.getName().length() == 0) {
			throw new EmptyFieldException("601", "Field are emply please fill the right input");
		} else {
			entity = repository.save(entity);

			return entity;
		}
	}

	public void deleteEmployeeById(int id) {

		Optional<Person> employee = repository.findById(id);
		if (employee.isPresent()) {
			repository.deleteById(id);
		} else
			throw new NoSuchElementException();

	}

	public List<Person> getSalary() {
		List<Person> list = repository.findAll();
//		List<Person> list1 = new ArrayList();
//		Predicate<Person> p = e -> e.getSalary() > 20000;
//		for (Person p1 : list) {
//			if (p.test(p1))  list.toStream().(i->i.getSalary()>2000).collect(Collectors.toList())
		
//				list1.add(p1);
//
//		}
// return list1;
//		List<Person> list1 = new ArrayList();
//		Iterator i = list.iterator();
//		while (i.hasNext()) {
//			Person p = (Person) i.next();
//			if (p.getSalary() > 20000) {
//				list1.add(p);
//
//			}
//
//		}
//		return list1;
//	}
//}
		List<Person> l2 = list.stream().filter(e -> e.getSalary() > 20000).collect(Collectors.toList());
		return l2;

	}

	public Long getCount() {
		List<Person> list = repository.findAll();
		Long count = list.stream().count();
		return count;

	}

	public List<Person> sortByName() {
		List<Person> list = repository.findAll();
		list = list.stream().sorted((e1, e2) -> e1.getName().compareToIgnoreCase(e2.getName()))
				.collect(Collectors.toList());
		return list;

	}
}

/*
 * public Person save1(Person person) { Person p = repository.save(person);
 * 
 * return p; }
 */

//}
