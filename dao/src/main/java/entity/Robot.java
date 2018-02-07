package entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Class Robot includes fields <b>numberRobot</b> and <b>Work</b>. The name of table in database is "robots"
 * @see Robot
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "robots")
public class Robot extends BaseEntity {


    @Column(name = "number_robot")
    /**
     * Field {@link Robot.numberRobot} is in application. Column in database is "number_robots"
     */
    private String numberRobot;


    @ManyToOne
    @JoinColumn(name = "work_id")
    /**
     * Field {@link Robot.work} is work that must be done. Column in database is "work_id"
     */
    private Work work;
}
