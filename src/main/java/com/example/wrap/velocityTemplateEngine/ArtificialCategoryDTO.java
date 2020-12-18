package com.example.wrap.velocityTemplateEngine;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaochengju
 */
@Data
public class ArtificialCategoryDTO implements Serializable {
    private static final long serialVersionUID = 8105492058151064385L;

    /**
     * 品类ID
     */
    @ApiModelProperty(value = "品类ID", required = true)
    private Long id;

    /**
     * 品类名称
     */
    @ApiModelProperty(value = "品类名称", required = true)
    private String name;

    /**
     * 品类全称
     */
    @ApiModelProperty(value = "品类全称", required = true)
    private String fullName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remarks;

    /**
     * 所属分组
     */
    @ApiModelProperty(value = "所属分组", required = true)
    private Long parentId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    private Boolean enabled;
}
