package wtf.wtfgames.wtfwords.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="testuser")
public class User extends BaseModel {
	@Column(name="login")
    private String login;

	@Column(name="password")
	private String password;

	public User() {}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
}
