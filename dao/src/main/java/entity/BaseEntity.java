package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * There is abstract class from which it is necessary to inherit new entity that is needed <b>ID</b> in the database.
 */
@MappedSuperclass
@ToString
public abstract class BaseEntity {

    /**
     * Field id for save entity in database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
}
