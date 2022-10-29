package com.smart.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="CONTACT")
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cId;
	private String name;
	private String secondName;
	private String work;
	private String email;
	private String phone;
	private String image;

	private String doj;

	private String password;

	@Column(length=1000)
	private String description;

	private long code;
	@ManyToOne
	private User user;
	private String role;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="contact")
	private List<Attendence> attendence=new ArrayList<>();
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(int cId, String name, String secondName, String work, String email, String phone, String image, String doj, String password, String description, long code, User user, String role, List<Attendence> attendence) {
		this.cId = cId;
		this.name = name;
		this.secondName = secondName;
		this.work = work;
		this.email = email;
		this.phone = phone;
		this.image = image;
		this.doj = doj;
		this.password = password;
		this.description = description;
		this.code = code;
		this.user = user;
		this.role = role;
		this.attendence = attendence;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Attendence> getAttendence() {
		return attendence;
	}

	public void setAttendence(List<Attendence> attendence) {
		this.attendence = attendence;
	}

//	@Override
//	public String toString() {
//		return "Contact [cId=" + cId + ", name=" + name + ", secondName=" + secondName + ", work=" + work + ", email="
//				+ email + ", phone=" + phone + ", image=" + image + ", description=" + description + ", user=" + user
//				+ "]";
//	}



}
