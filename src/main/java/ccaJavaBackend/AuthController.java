package ccaJavaBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
@RequestMapping("/auth")
public class AuthController
{

    @Autowired
    private UsersRepository UsersRepository;

    @Autowired
    private UserService UserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/login")
    public Object loginRoute(@RequestBody HashMap<String, String> request)
    {
        Users foundUser = UsersRepository.findByUsername(request.get("username"));
        boolean valid = bCryptPasswordEncoder.matches(request.get("password"), foundUser.getPassword());

        HashMap<String, String> response = new HashMap<>();

        if (valid)
        {
            response.put("success", "true");
            response.put("userId", foundUser.getId().toString());
        }
        else
        {
            response.put("success", "false");
        }

        return response;
    }
}
