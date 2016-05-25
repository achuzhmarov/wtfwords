package wtf.wtfgames.wtfwords.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wtfuser")
public class User extends BaseModel {
	@Column(name="login")
    private String login;

	@Column(name="password")
	private String password;

	public User() {}

	public User(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String name) {
		this.login = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
