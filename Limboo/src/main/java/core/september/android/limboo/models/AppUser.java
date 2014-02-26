package core.september.android.limboo.models;

import com.niusounds.sqlite.Persistence;
import com.niusounds.sqlite.PrimaryKey;

import core.september.android.limboo.ifaces.CRUDable;

/**
 * Created by christian on 19/02/14.
 */
public class AppUser implements CRUDable {
    @Persistence
    @PrimaryKey(autoIncrement = true)
    private long _id;

    @Persistence
    private String userName;
    @Persistence
    private String password;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
