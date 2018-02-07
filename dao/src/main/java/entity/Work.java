package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "works")
/**
 * Robots class with properties <b>numberRobot</b> and <b>work</b>. Table on database "works"
 */
public class Work extends BaseEntity {

    @Column(name = "work")
    /**
     * Field works. Column on database "work"
     */
    private String works;

    @OneToMany(mappedBy = "work")
    /**
     * Field robots. All robots in this work
     */
    private Set<Robot> robots = new HashSet<>();

    @Column(name = "size_work")
    /**
     * Field sizeWork. Column on database "size_work"
     */
    private int sizeWork;
}
