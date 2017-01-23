package wtf.wtfgames.wtfwords.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="acquired_reward")
public class AcquiredReward extends BaseModel  {
    @Column(name="user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;

    public AcquiredReward() {}
}
