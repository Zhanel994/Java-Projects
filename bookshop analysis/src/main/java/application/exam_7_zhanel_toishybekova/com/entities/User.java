package application.exam_7_zhanel_toishybekova.com.entities;

import application.exam_7_zhanel_toishybekova.com.models.Authority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(hidden = true)
@Document(collection = "users")
public class User implements UserDetails
{

    @Id
    private String id = UUID.randomUUID().toString();

    private String username;
    private String password;

    private String phone;

    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority authority)
    {
        authorities.add(authority);
    }
}
