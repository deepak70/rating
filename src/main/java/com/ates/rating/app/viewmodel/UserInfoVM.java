package com.ates.rating.app.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoVM {
    private String emailId;
    private String name;
    @JsonIgnore
    private String password = "Ates@12345";
}
