package org.jigang.validation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jigang.validate.NotNullEntity;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by wujigang on 16/7/11.
 */
//@NotNullEntity(message = "校验对象不能为空")
public class ValidatePojo {
//    @NotBlank
    private Integer id;

    @NotBlank(message="名字不能为空或者空串")
    @Length(min=2,max=10,message="名字必须由2~10个字组成")
    private String name;

    @Past(message="时间不能晚于当前时间")
    private Date day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
