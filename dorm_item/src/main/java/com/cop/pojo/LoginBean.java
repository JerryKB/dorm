package com.cop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginBean {
    private String username;
    private String realName;
    private Integer identity;

   public static LoginBean getLogin(String username,String realName,Integer identity){
       return new LoginBean(username,realName, identity);

   }
}
