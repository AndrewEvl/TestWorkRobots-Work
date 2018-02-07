package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@ToString
/**
 * Abstract class from which it is necessary to inherit new entity that need <b>id</b> in the database.
 * @param id - id entity.
 */
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
}
