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
public class Work extends BaseEntity {

    @Column(name = "work")
    private String works;
    @OneToMany(mappedBy = "work")
    private Set<Robot> robots = new HashSet<>();
    @Column(name = "status_work")
    private StatusWork statusWork;
    @Column(name = "size_work")
    private Long sizeWork;
}
