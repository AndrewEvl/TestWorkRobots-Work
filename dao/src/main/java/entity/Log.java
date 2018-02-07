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
 * Robots class with properties <b>log</b> and <b>time</b>. Table on database "log"
 */
public class Log extends BaseEntity {

    /**
     * Field log. Column on database "log"
     */
    @Column(name = "log")
    private String log;

    /**
     * Field log time create. Column on database "time"
     */
    @Column(name = "time")
    private LocalDateTime time;
}
