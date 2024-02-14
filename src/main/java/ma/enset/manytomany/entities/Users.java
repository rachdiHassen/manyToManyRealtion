package ma.enset.manytomany.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Users {
    @Id
    private String userId;
    @Column(length = 20, unique = true)
    private String userName;
    private String password;
    @ManyToMany(mappedBy ="users", fetch = FetchType.EAGER)
    private List<Roles> roles=new ArrayList<>();
}
