package ApiThirteenthHw;

import lombok.Data;
import pojo.Notes;
import pojo.RolesLombok;

import java.util.List;

@Data
public class CreateUserDTO {
    private String login;
    private String password;
    private String email;
    private List<RolesLombok> roles;
    private List<Notes> notes;
}
