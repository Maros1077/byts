package cz.zelo.byts.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class User {

    private String idid;

    private String email;

    private Date registered;

    private Date lastAccess;

}
