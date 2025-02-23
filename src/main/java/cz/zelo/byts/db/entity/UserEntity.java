package cz.zelo.byts.db.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    Long id;

    @Column(name = "IDID", nullable = false)
    private String idid;

    @Column(name = "REGISTERED", nullable = false)
    private Date registered;

    @Column(name = "LAST_ACCESS")
    private Date lastAccess;

}
