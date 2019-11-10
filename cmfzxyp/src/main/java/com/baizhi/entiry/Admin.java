package com.baizhi.entiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @Id
    private String id;
    private String username;
    private String password;
    private String nickname;
}
