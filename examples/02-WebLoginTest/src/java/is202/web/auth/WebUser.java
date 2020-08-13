/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is202.web.auth;

/**
 *
 * @author evenal
 */
public class WebUser {

    private String username;
    private String password;

    public WebUser(String usr, String pwd) {
        this.username = usr;
        this.password = pwd;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
