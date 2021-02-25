package ttl.larku.domain;

import javax.persistence.*;

@Entity
public class Student {
	
	public enum Status { 
		FULL_TIME(0),
		PART_TIME(2),
		HIBERNATING(3);
		private int num;
		Status(int num) {
			this.num = num;
		}
		public int getNum() {
			return num;
		}
	};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private String phoneNumber;

//	@Enumerated(EnumType.STRING)
	@Convert(converter = StatusConverter.class)
	private Status status = Status.FULL_TIME;
	
	public Student() {
		this("Unknown", "", Status.FULL_TIME);
	}
	
	public Student(String name, String phoneNumber, Status status) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}

class StatusConverter implements AttributeConverter<Student.Status, String>
{

	@Override
	public String convertToDatabaseColumn(Student.Status attribute) {
		return attribute.toString();
	}

	@Override
	public Student.Status convertToEntityAttribute(String dbData) {
		return Student.Status.valueOf(dbData);
	}
}
