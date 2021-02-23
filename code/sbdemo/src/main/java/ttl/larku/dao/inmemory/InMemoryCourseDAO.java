package ttl.larku.dao.inmemory;

import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCourseDAO implements BaseDAO<Course> {

	private Map<Integer, Course> courses = new HashMap<Integer, Course>();
	private static AtomicInteger nextId = new AtomicInteger(20);
	
	private String from;
	public InMemoryCourseDAO(String from) {
		this.from = from + ": ";
	}
	
	public InMemoryCourseDAO() {
		this("InMem");
	}

	public void update(Course updateObject) {
		if(courses.containsKey(updateObject.getId())) {
			courses.put(updateObject.getId(), updateObject);
		}
	}

	public void delete(Course course) {
		courses.remove(course.getId());
	}

	public Course create(Course newObject) {
		//Create a new Id
		int newId = nextId.getAndIncrement();
		newObject.setId(newId);
		newObject.setCode(from + newObject.getCode());
		courses.put(newId, newObject);
		
		return newObject;
	}

	public Course get(int id) {
		return courses.get(id);
	}

	public List<Course> getAll() {
		return new ArrayList<Course>(courses.values());
	}
	
	public void deleteStore() {
		courses = null;
	}
	
	public void createStore() {
		courses = new HashMap<Integer, Course>();
	}
	
	public Map<Integer, Course> getCourses() {
		return courses;
	}

	public void setCourses(Map<Integer, Course> courses) {
		this.courses = courses;
	}
}
