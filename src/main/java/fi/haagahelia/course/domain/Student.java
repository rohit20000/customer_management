package fi.haagahelia.course.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
/*
{
"first_name": "Jane",
"last_name": "Doe",
"street": "Elvnu Street",
"address": "H no 2 ",
"city": "Delhi",
"state": "Delhi",
"email": "sam@gmail.com",
"phone": "12345678"
}
 */
@Entity
public class Student {
	private long id;	 
	private String firstName;	
	private String lastName;
	private String Street;
	private String Address;
	private String City;
	private String State;

	private String email;
	private String Phone;

	private Set<Course> courses = new HashSet<Course>(0);    
    
    public Student() {
    }

	public Student(String firstName, String lastName,String Street , String Address , String City , String State , String email , String Phone) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.Street = Street;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.email = email;
        this.Phone = Phone;
    }



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    @Column(name = "firstname")   	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    @Column(name = "lastname")	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name ="street")
  	public String getStreet() {
		return Street;
	}
	public void setStreet(String Street) {
		this.Street = Street;
	}
	@Column(name ="address")
	public String getAddress() {
		return Address;
	}
	public void setAddress(String Address) {

		this.Address = Address;
	}
	@Column(name="city")
	public String getCity() {
		return City;
	}
	public void setCity(String City) {
		this.City = City;
	}
	@Column(name ="state")
	public String getState() {
		return State;
	}
	public void setState(String State) {
		this.State = State;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return Phone;
	}

		@Column(name="phone")
		public void setPhone(String Phone) {
		 this.Phone = Phone;
		}
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "courseid") })
	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public boolean hasCourse(Course course) {
		for (Course studentCourse: getCourses()) {
			if (studentCourse.getCourseid() == course.getCourseid()) {
				return true;
			}
		}
		return false;
	}	
}
