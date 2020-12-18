package com.ikongjian.generate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangxiaoyu
 * @date 2020/12/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelClassDocVO {

    private String modelTableName;

    private String modelClassName;

    private String modelCommentText;

    private List<FieldEntry> fields;

}
