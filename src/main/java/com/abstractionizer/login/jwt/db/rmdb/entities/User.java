package com.abstractionizer.login.jwt.db.rmdb.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "user")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private boolean isActivated;

    private Integer status;
}
