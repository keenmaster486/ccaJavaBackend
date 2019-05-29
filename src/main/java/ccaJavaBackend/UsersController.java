package ccaJavaBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository UsersRepository;

    //@Autowired
    //private PostRepository PostRepository;

    @Autowired
    private UserService UserService;

    @GetMapping
    public Iterable<Users> indexUsers(){
        return UsersRepository.findAll();
    }


    @PostMapping
    public HashMap<String, String> user(@RequestBody HashMap<String, String> request){
        //System.out.println(request);

        HashMap<String, String> response = new HashMap<>();

        System.out.println(request.get("password"));
        System.out.println(request.get("password2"));

        if (!request.get("password").equals(request.get("password2")))
        {
            //passwords do not match!
            System.out.println("if thing");
            response.put("success", "false");
            return response;
        }
        System.out.println("outside of the if thing");



        Users newUser = new Users();
        newUser.setUsername(request.get("username"));
        newUser.setPassword(request.get("password"));
        newUser.setUsertype("std"); //ALL standard users for now!!
        newUser.setEmail("");
        newUser.setDisplayname(request.get("displayname"));

        UserService.saveUser(newUser);

        response.put("success", "true");
        return response;
    }


    @GetMapping("/{id}")
    public Object show(@PathVariable("id") Long id) throws Exception
    {
        Optional<Users> response = UsersRepository.findById(id);
        if (response.isPresent()){
            Users user = response.get();
            //Iterable<Post> posts = PostRepository.findByUser(user);
            //HashMap<String, Object> results = new HashMap<>();
            //results.put("user", user);
            //results.put("posts", posts);
            //return user;

            HashMap<String, String> mainResponse = new HashMap<>();

            mainResponse.put("_id", id.toString());
            mainResponse.put("username", user.getUsername());
            mainResponse.put("password", user.getPassword());
            mainResponse.put("usertype", user.getUsertype());
            mainResponse.put("email", user.getEmail());
            mainResponse.put("displayname", user.getDisplayname());

            return mainResponse;
        }
        throw new Exception("User does not exist");
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        UsersRepository.deleteById(id);
        return "Deleting " + id;

    }


    @PutMapping("/{id}")
    public Users put(@RequestBody Users updatedUsers, @PathVariable("id") Long id) throws Exception{
        Optional<Users> response = UsersRepository.findById(id);
        if (response.isPresent()){
            Users user = response.get();
            user.setUsername(updatedUsers.getUsername());
            return UsersRepository.save(user);
        }
        throw new Exception("User to update not found");
    }

}
