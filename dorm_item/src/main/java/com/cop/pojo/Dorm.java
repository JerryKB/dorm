package com.cop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_dorm")
@ApiModel(value="Dorm对象", description="")
public class Dorm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String bid;

    private Integer now_count;

    private Integer contain_count;

    private Integer situation;

    @TableField(exist = false)
    private List<String> stdName;


}
