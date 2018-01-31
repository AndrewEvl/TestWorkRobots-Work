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
public class Robot extends BaseEntity {

    @Column(name = "number_robot")
    private Long numberRobots;
    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;
    @Column(name = "status_robot")
    private StatusRobot statusRobot;
}
