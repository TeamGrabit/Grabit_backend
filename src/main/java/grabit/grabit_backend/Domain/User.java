package grabit.grabit_backend.Domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
public class User implements UserDetails {

	@Id
	@Column(name="ID")
	private Integer Id;

	@NotNull
	@Column(name="USER_ID")
	private String userId;

	@Column(name="USER_NAME")
	private String username;
	@NotNull
	@Column(name="USER_EMAIL")
	private String userEmail;

	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime modifiedAt;

	private boolean enabled = true;

	public User(Integer Id, String userId, String userName, String userEmail) {
		this.Id = Id;
		this.username = userName;
		this.userId = userId;
		this.userEmail = userEmail;
		this.enabled = true;
	}

	public User() { }

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public Integer getId() {
		return Id;
	}
	public String getUserId() {
		return userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority("ROLE_USER"));
		return list;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public User update(String userId, String userName, String userEmail) {
		this.userId = userId;
		this.username = userName;
		this.userEmail = userEmail;
		return this;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
