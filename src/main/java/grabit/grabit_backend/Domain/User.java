package grabit.grabit_backend.Domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class User {

	@Id
	@Column(name="UNIQUE_ID")
	private Integer uniqueId;

	@NotNull
	@Column(name="USER_ID")
	private String userId;

	@Column(name="USER_NAME")
	private String userName;
	@NotNull
	@Column(name="USER_EMAIL")
	private String userEmail;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name="ROLE")
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
