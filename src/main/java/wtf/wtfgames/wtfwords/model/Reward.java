package wtf.wtfgames.wtfwords.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.LocalDate;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name="reward")
public class Reward extends BaseModel {
    @Column(name="code", unique = true)
    private String code;

    @Column(name="wtfs")
    private int wtfs;

    @Column(name="message", unique = true)
    private String message;

    @Column(name="uses_limit")
    private Long usesLimit;

    @Column(name="time_limit")
    private LocalDate timeLimit;

    @Transient
    public boolean isExpired() {
        if (usesLimit != null && usesLimit <= 0) {
            return true;
        }

        if (timeLimit != null && LocalDate.now().compareTo(timeLimit) > 0) {
            return true;
        }

        return false;
    }

    public Reward() {}
}
