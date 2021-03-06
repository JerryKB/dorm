package com.cop.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_building")
@ApiModel(value="Building对象", description="")
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer bid;

    private Integer floor;

    private String info;


}
