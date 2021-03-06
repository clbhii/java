package com.cl.spring.mvc;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

public class User {
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", birth=" + birth + "]";
    }    
    private int id;
    @NotEmpty
    private String name;
 
    /**
     * @Past表示时间必须是一个过去值
     */
    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;
}
