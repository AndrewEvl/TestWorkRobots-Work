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
    private String numberRobot;
    @ManyToOne
    @JoinColumn(name = "work_id")
    private Work work;
}
