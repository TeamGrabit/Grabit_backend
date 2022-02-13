package grabit.grabit_backend.Domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User extends BaseEntity implements UserDetails{

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
