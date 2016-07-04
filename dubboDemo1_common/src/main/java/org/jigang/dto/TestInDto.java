package org.jigang.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by wujigang on 16/7/1.
 */
public class TestInDto {
    @NotNull
    @Min(value = 2, message = "id不能小于2")
    @Max(value = 8, message = "id不能大于8")
    private Integer id;

    @NotNull
    @Pattern(regexp = "^[\\w\\s\u4e00\u9fa5]{1,10}$")
    private String name;

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
}
