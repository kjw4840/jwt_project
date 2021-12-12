package jwt.prac.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Role {

    @Id @GeneratedValue
    @Column(name = "role_id")
    private Long roleId;

    private String name;

}
