package edu.uco.kpatel19.onelist;

/**
 * Created by Cary on 3/22/2016.
 */
public class UserInfo {

    private int _id;
    private String _username;
    private String _listname;

    public UserInfo() {

        _username = "Register/Sign In";
    }

    public UserInfo(String username) {

        _username = username;
        _listname = username + "_Lists";
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

    public String get_listname() {
        for (int i = 0; i < _listname.length(); i++){
              if(_listname.charAt(i)=='_')
              return _listname;
        }
        return _listname + "_Lists" ;
    }

    public void set_listname(String listname) {
        this._listname = listname + "_Lists";
    }
}
