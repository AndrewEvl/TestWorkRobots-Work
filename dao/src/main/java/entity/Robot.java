package entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Class Robot includes fields <b>numberRobot</b> and <b>Work</b>. The name of table in database is "robots"
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "robots")
public class Robot extends BaseEntity {

    /**
     * Field {@link Robot} number is in application. Column in database is "number_robots"
     */
    @Column(name = "number_robot")
    private String numberRobot;

    /**
     * Field {@link Robot} is work that must be done. Column in database is "work_id"
     */
    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;
}
