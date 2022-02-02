package grabit.grabit_backend.Domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@Column(name="uniqueId")
	private Integer uniqueId;

	@NotNull
	@Column(name="userId")
	private String userId;

	@Column(name="userName")
	private String userName;
	@NotNull
	@Column(name="userEmail")
	private String userEmail;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Role role;

	public User(Integer uniqueId, String userId, String userName, String userEmail,Role role) {
		this.uniqueId = uniqueId;
		this.userName = userName;
		this.userId = userId;
		this.userEmail = userEmail;
		this.role = role;
	}

	public User() { }

	public Integer getUniqueId() {
		return uniqueId;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public Role getRole() {
		return role;
	}
	public String getRoleKey(){
		return this.role.getKey();
	}
	public User update(String userId, String userName, String userEmail) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		return this;
	}
}
