package app.college.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Courses {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<Enrollments> enrollments;
}
