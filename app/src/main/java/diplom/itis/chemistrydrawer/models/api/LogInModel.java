package diplom.itis.chemistrydrawer.models.api;

/**
 * Created by Денис on 11.02.2017.
 */

public class LogInModel {

    public String user;
    public String password;

    public LogInModel() {}

    public LogInModel(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
