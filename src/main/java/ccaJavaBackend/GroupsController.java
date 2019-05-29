package ccaJavaBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    @Autowired
    private GroupsRepository GroupsRepository;

    @Autowired
    private GroupService GroupService;

    @Autowired
    private MessagesRepository MessagesRepository;

    @Autowired
    private MessageService MessageService;


    @GetMapping
    public Iterable<Groups> indexGroups()
    {
        return GroupsRepository.findAll();
    }


    @PostMapping
    public HashMap<String, String> group(@RequestBody HashMap<String, String> request){
        //System.out.println(request);

        HashMap<String, String> response = new HashMap<>();

        Groups newGroup = new Groups();

        newGroup.setUserId(request.get("userId"));

        newGroup.setName(request.get("name"));
        newGroup.setCategory(request.get("category"));
        newGroup.setTopic(request.get("topic"));
        newGroup.setType(request.get("type"));
        newGroup.setJoinpolicy(Integer.parseInt(request.get("joinpolicy")));

        if (request.get("private").equals("on"))
        {
            newGroup.setPrivate(Boolean.TRUE);
        }
        else
        {
            newGroup.setPrivate(Boolean.FALSE);
        }

        if (request.get("allowinvite").equals("on"))
        {
            newGroup.setAllowinvite(Boolean.TRUE);
        }
        else
        {
            newGroup.setAllowinvite(Boolean.FALSE);
        }

        GroupService.saveGroup(newGroup);

        response.put("success", "true");

        return response;
    }


    @GetMapping("/{id}")
    public Object show(@PathVariable("id") Long id) throws Exception
    {
        Optional<Groups> response = GroupsRepository.findById(id);
        if (response.isPresent()){
            Groups group = response.get();
            //Iterable<Post> posts = PostRepository.findByUser(user);
            //HashMap<String, Object> results = new HashMap<>();
            //results.put("user", user);
            //results.put("posts", posts);
            //return user;

            HashMap<String, Object> mainResponse = new HashMap<>();
//
//            mainResponse.put("_id", id.toString());
//            mainResponse.put("username", user.getUsername());
//            mainResponse.put("password", user.getPassword());
//            mainResponse.put("usertype", user.getUsertype());
//            mainResponse.put("email", user.getEmail());
//            mainResponse.put("displayname", user.getDisplayname());
            mainResponse.put("id", id.toString());
            mainResponse.put("name", group.getName());
            mainResponse.put("topic", group.getTopic());
            mainResponse.put("type", group.getType());
            mainResponse.put("private", group.getPrivate());
            mainResponse.put("joinpolicy", group.getJoinpolicy());
            mainResponse.put("allowinvite", group.getAllowinvite());


            ArrayList<Messages> foundMsgs = MessagesRepository.findByGroupId(id);

            mainResponse.put("msgLength", foundMsgs.size());

            return mainResponse;
        }
        throw new Exception("Group does not exist");
    }


    @PostMapping("/{id}/messages")
    public HashMap<String, String> message(@PathVariable("id") Long id, @RequestBody HashMap<String, String> request){
        //System.out.println(request);

        HashMap<String, String> response = new HashMap<>();


        Messages newMessage = new Messages();

        newMessage.setGroupId(id);
        newMessage.setUserId(request.get("userId"));
        newMessage.setUserdisplayname(request.get("userdisplayname"));
        newMessage.setText(request.get("text"));
        newMessage.setImage(request.get("image"));

        MessageService.saveMessage(newMessage);

        response.put("success", "true");

        return response;
    }

    @GetMapping("/{id}/messages/{startmsg}/{endmsg}")
    public Object showMessages(@PathVariable("id") Long id, @PathVariable("startmsg") Integer startmsg, @PathVariable("endmsg") Integer endmsg) throws Exception
    {
        Optional<Groups> response = GroupsRepository.findById(id);
        if (response.isPresent()){
            Groups group = response.get();

            //Object mainResponse = new Object();

            ArrayList<Messages> foundMsgs = MessagesRepository.findByGroupId(id);

            return foundMsgs.subList(startmsg, endmsg);
        }
        throw new Exception("Group does not exist");
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        GroupsRepository.deleteById(id);
        return "Deleting " + id;

    }


    @PutMapping("/{id}")
    public Groups put(@RequestBody Groups updatedGroups, @PathVariable("id") Long id) throws Exception{
        Optional<Groups> response = GroupsRepository.findById(id);
        if (response.isPresent()){
            Groups group = response.get();
            //user.setUsername(updatedUsers.getUsername());
            return GroupsRepository.save(group);
        }
        throw new Exception("Group to update not found");
    }

}
