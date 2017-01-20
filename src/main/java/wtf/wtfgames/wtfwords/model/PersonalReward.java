package wtf.wtfgames.wtfwords.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="personal_reward")
public class PersonalReward extends BaseModel {
	@Column(name="user_id")
    private String userId;

	@Column(name="message")
	private String message;

	@Column(name="wtfs")
	private int wtfs;

	@Column(name="acquired")
	private boolean acquired;

	public PersonalReward() {}
}
