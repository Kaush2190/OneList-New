package edu.uco.kpatel19.onelist;

/**
 * Created by Cary on 3/22/2016.
 */
public class UserInfo {

    private int _id;
    private String _username;
    //private String _password;

    public UserInfo() {

        _username = "Register/Sign In";
       // _password = "";
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String username) {
        this._username = username;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }
}
