package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;




@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "log")
/**
 * Class Log includes fields <b>log</b> and <b>time</b>. The name of table in database is "log"
 */
public class Log extends BaseEntity {


    @Column(name = "log")
    /**
     * Field is {@link Log.log}. Column in database is "log"
     */
    private String log;


    @Column(name = "time")
    /**
     * Field {@link Log.time} is time of creation log. Column in database is "time"
     */
    private LocalDateTime time;
}
