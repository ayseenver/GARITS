package teamproject.User_Accounts;

public class User {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String roleName;

    public User() {
    }
    
    public User(User u) {
        setUsername(u.getUsername());
        setFirstName(u.getFirstName());
        setLastName(u.getLastName());
        setPassword(u.getPassword());
        setRoleName(u.getRoleName());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    

}
