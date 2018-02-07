package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class work includes fields <b>numberRobot</b> and <b>work</b> and. The name of table in database is "works"
 */
@Entity
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "works")
public class Work extends BaseEntity {

    /**
     * Field works. Column in database "work"
     */
    @Column(name = "work")
    private String works;

    /**
     * Field robots. All robots which make this work
     */
    @OneToMany(mappedBy = "work")
    private Set<Robot> robots = new HashSet<>();

    /**
     * Field sizeWork.How many robots need for this job. Column in database "size_work"
     */
    @Column(name = "size_work")
    private int sizeWork;
}
