package org.jigang.validate;

import javax.validation.constraints.NotNull;

/**
 * Created by wujigang on 16/7/13.
 */
@NotNullEntity(message = "校验对象不能为空")
public class Pojo {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
