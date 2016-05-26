package wtf.wtfgames.wtfwords.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
abstract class BaseModel {
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
}
