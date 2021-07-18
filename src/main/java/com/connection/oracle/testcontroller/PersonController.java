package com.connection.oracle.testcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connection.oracle.entity.JwtRequest;
import com.connection.oracle.entity.JwtResponse;
import com.connection.oracle.entity.Person;
import com.connection.oracle.jwtHelper.JwtUtil;
import com.connection.oracle.repository.serviceImpl.MyUserDetailsService;
import com.connection.oracle.repository.serviceImpl.PersonDaoServiceImpl;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	PersonDaoServiceImpl service;

	@Autowired
	JwtUtil jwtHelper;

	@Autowired
	MyUserDetailsService service1;

	@Autowired
	private AuthenticationManager authenticationManager;

//	@RequestMapping("/")
//	public String getHomePage(Model model) {
//		model.addAttribute("successMsg", "Welcome to My Code");
//
//		return "Homepage";
//	}

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		}

		catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");

		}

		// fine area then below line will excute

		UserDetails userDetails = service1.loadUserByUsername(jwtRequest.getUsername()); // here we will get user
																							// details
		String token = jwtHelper.generateToken(userDetails);
		System.out.println("Jwt token" + token);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@GetMapping("/getpersons")
	public ResponseEntity<List<Person>> getAllPerson() { // List of person
		List<Person> list = service.getAllEmployees();
		if (list.size() <= 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // This build method will create obj of ResEnt

		return ResponseEntity.of(Optional.of(list));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable int id) {

		Person person = service.getId(id);

		return new ResponseEntity<Person>(person, new HttpHeaders(), HttpStatus.OK);

	}

	@GetMapping("/numberofemployee")
	Long countEmployee() {
		long count = service.getCount();
		return count;
	}

	@GetMapping("/sortbyname")
	public ResponseEntity<List<Person>> sortByEmployeeName() {
		List<Person> list = service.sortByName();
		return ResponseEntity.of(Optional.of(list));

	}
	// @PostMapping("/person")
	/*
	 * Person createOrUpdateEmployee(Person person) { Person update =
	 * service.save1(person); return update; }
	 */

	@PostMapping("/person")
	public ResponseEntity<Person> createOrUpdateEmployee(@RequestBody Person employee) // RequestBody is used to convert
																						// jason into java object
	{
		Person updated = service.createOrUpdateEmployee(employee);
		return new ResponseEntity<Person>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id) {

		service.deleteEmployeeById(id);
		return new ResponseEntity<String>("value has been deleted", HttpStatus.OK);

	}

	@GetMapping("/getSalaryabove20k")
	public List<Person> getSalaryabove() {
		List<Person> list = service.getSalary();
		return list;
	}

}
