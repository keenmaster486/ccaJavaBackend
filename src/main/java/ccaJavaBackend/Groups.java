package ccaJavaBackend;

import javax.persistence.*;

@Entity
public class Groups
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String userId;

    private String name;
    private String category;
    private String topic;
    private String type;
    private Boolean whetherprivate;
    private Integer joinpolicy;
    private Boolean allowinvite;


    public Long getId () {return this.id;}
    public void setId (Long id) {this.id = id;}


    public String getUserId () {return this.userId;}
    public void setUserId (String userId) {this.userId = userId;}


    public String getName () {return this.name;}
    public void setName (String name) {this.name = name;}

    public String getCategory () {return this.category;}
    public void setCategory (String category) {this.category = category;}

    public String getTopic () {return this.topic;}
    public void setTopic (String topic) {this.topic = topic;}

    public String getType () {return this.type;}
    public void setType (String type) {this.type = type;}

    public Boolean getPrivate () {return this.whetherprivate;}
    public void setPrivate (Boolean whetherprivate) {this.whetherprivate = whetherprivate;}

    public Integer getJoinpolicy () {return this.joinpolicy;}
    public void setJoinpolicy (Integer joinpolicy) {this.joinpolicy = joinpolicy;}

    public Boolean getAllowinvite () {return this.allowinvite;}
    public void setAllowinvite (Boolean allowinvite) {this.allowinvite = allowinvite;}
}