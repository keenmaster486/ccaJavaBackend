package ccaJavaBackend;


import javax.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    private String username;

    private String password;

    private String usertype;

    private String email;

    private String displayname;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String nameInput){
        this.username = nameInput;
    }

    public Long getId(){
        return this._id;
    }

    public void setId(Long id){
        this._id = id;
    }

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }




    public String getUsertype() {return this.usertype;}
    public void setUsertype(String usertype) {this.usertype = usertype;}

    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}

    public String getDisplayname() {return this.displayname;}
    public void setDisplayname(String displayname) {this.displayname = displayname;}

}