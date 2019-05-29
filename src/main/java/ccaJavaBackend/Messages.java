package ccaJavaBackend;

import javax.persistence.*;

@Entity
public class Messages
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;


    private String userId;
    private String userdisplayname;
    private String text;
    private String image;
    private String video;
    private String url;
    private Long groupId;


    public Long getId () {return this._id;}
    public void setId (Long id) {this._id = id;}

    public String getUserId () {return this.userId;}
    public void setUserId (String userId) {this.userId = userId;}

    public String getUserdisplayname () {return this.userdisplayname;}
    public void setUserdisplayname (String userdisplayname) {this.userdisplayname = userdisplayname;}

    public String getText () {return this.text;}
    public void setText (String text) {this.text = text;}

    public String getImage () {return this.image;}
    public void setImage(String image) {this.image = image;}

    public String getVideo () {return this.video;}
    public void setVideo (String video) {this.video = video;}

    public String getUrl () {return this.url;}
    public void setUrl (String url) {this.url = url;}



    public Long getGroupId () {return this.groupId;}
    public void setGroupId (Long groupId) {this.groupId = groupId;}


}