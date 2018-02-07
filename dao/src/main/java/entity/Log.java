package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;



/**
 * Class Log includes fields <b>log</b> and <b>time</b>. The name of table in database is "log"
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "log")
public class Log extends BaseEntity {

    /**
     * Field is text log. Column in database is "log"
     */
    @Column(name = "log")
    private String log;

    /**
     * Field is time of creation log. Column in database is "time"
     */
    @Column(name = "time")
    private LocalDateTime time;
}
