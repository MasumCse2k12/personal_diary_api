package com.impelitsolutions.service.idm.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private String userId;
    private String password;
    private Integer status;

    public User(com.impelitsolutions.service.idm.model.User userEO){
        if(userEO != null){
            this.id = userEO.getId();
            this.name = userEO.getName();
            this.userId = userEO.getUsername();
            this.password = userEO.getPassword();
            this.status = userEO.getStatus();
        }
    }

}
