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
 * Class work includes fields <b>numberRobot</b> and <b>work</b> and . The name of table in database is "works"
 */
public class Work extends BaseEntity {

    @Column(name = "work")
    /**
     * Field {@link Work.works}. Column in database "work"
     */
    private String works;

    @OneToMany(mappedBy = "work")
    /**
     * Field {@link Work.robots}. All robots which make this work
     */
    private Set<Robot> robots = new HashSet<>();

    @Column(name = "size_work")
    /**
     * Field {@link Work.sizeWork}.How many robots need for this job. Column in database "size_work"
     */
    private int sizeWork;
}
