package com.cop.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_checkin_room")
@ApiModel(value="CheckinRoom对象", description="")
public class CheckinRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate time;

    private String did;


}
