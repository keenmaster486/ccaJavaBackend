package ccaJavaBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("GroupService")
public class GroupService {

    private GroupsRepository groupRepository;

    @Autowired
    public GroupService(GroupsRepository groupRepository)
    {
        this.groupRepository = groupRepository;
    }

//    public Groups findGroupById(long id)
//    {
//        return groupRepository.findById(id);
//    }

    public Groups saveGroup(Groups group)
    {
        groupRepository.save(group);
        return group;
    }

}