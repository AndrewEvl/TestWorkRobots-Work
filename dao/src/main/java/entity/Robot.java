package entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "robots")
/**
 * Robots class with properties <b>numberRobot</b> and <b>work</b>. Table on database "robots"
 */
public class Robot extends BaseEntity {

    @Column(name = "number_robot")
    /**
     * Field number robot on application. Column on database "number_robots"
     */
    private String numberRobot;
    @ManyToOne
    @JoinColumn(name = "work_id")
    /**
     * Field {@link Work} which must be done. Column on database "work_id"
     */
    private Work work;
}
