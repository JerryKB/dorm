package com.cop.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.io.Serializable;
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
@TableName("t_adjust_room")
@ApiModel(value="AdjustRoom对象", description="")
public class AdjustRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type=IdType.INPUT)
    private Integer id;

    @ApiModelProperty(value = "学生id")
    private String Sid;

    @ApiModelProperty(value = "目标宿舍")
    private String t_id;

    @ApiModelProperty(value = "原因")
    private String reason;

    private LocalDate edit_time;

    private LocalDate permit_time;

    private String permit_id;

    private Integer permit_state;


}
