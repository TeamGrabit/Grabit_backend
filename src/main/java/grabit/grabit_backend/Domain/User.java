package grabit.grabit_backend.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class User {
	@Id
	private Integer unique_id;
	private String user_id;
	private String user_name;
	private String user_email;

	@Enumerated(EnumType.STRING) // (1)
	@Column(nullable = false)
	private Role role;

	public User(Integer unique_id, String user_id, String user_name, String user_email,Role role) {
		this.unique_id = unique_id;
		this.user_name = user_name;
		this.user_id = user_id;
		this.user_email = user_email;
		this.role = role;

	}

	public User() {
	}


	public Integer getUnique_id() {
		return unique_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public Role getRole() {
		return role;
	}

	public String getRoleKey(){
		return this.role.getKey();
	}

	public User update(String user_id, String user_name, String user_email) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_email = user_email;
		return this;
	}
}
