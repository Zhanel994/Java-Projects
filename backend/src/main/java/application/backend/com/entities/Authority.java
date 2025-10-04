package application.backend.com.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import application.backend.com.utils.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
public class Authority extends BaseEntity implements GrantedAuthority {
    private String authority;
}
