package com.example.wrap.velocityTemplateEngine.dto;
import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
/**
 * @author zhangxiaoyu
 * @date 2020-12-01
 */
@Data
public class UserDTO implements Serializable {
    @ApiModelProperty(value = "品类ID")
    private Long id;
    @ApiModelProperty(value = "品类名称")
    private String name;
    @ApiModelProperty(value = "品类全称")
    private String fullName;
    @ApiModelProperty(value = "备注")
    private String remarks;
    @ApiModelProperty(value = "所属分组")
    private Long parentId;
    @ApiModelProperty(value = "状态")
    private Boolean enabled;
}