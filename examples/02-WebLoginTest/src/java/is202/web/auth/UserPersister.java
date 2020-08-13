/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is202.web.auth;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author evenal
 */
public class UserPersister {

    private Set<WebUser> users;

    public UserPersister() {
        this.users = new HashSet<WebUser>();
    }

    public void add(WebUser newUser) {
        if (users.contains(newUser)) {
            // TODO: notify user of invalid credentials
        }
        else {

        }
    }
}
