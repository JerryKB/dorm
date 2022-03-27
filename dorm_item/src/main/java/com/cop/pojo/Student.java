package com.cop.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiayu.Yang
 * @since 2021-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_student")
@ApiModel(value="Student对象", description="")
public class Student  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    private String real_name;

    private String role_name;

    private String sex;

    private String did;

    private String sclass;

    private String major;

    private Integer situation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkin_time;

    private Integer identity;


}
