package com.demo.shiro.bean;

/**
 * @Auther: ZHANG.HAO
 * @Date: 2019-02-20 17:57
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public abstract class BaseEntity<T extends Model> extends Model<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id ;

    /*
     * 租户编码
     */
    @TableField(value = "corp_code",fill = FieldFill.INSERT)
    protected String corpCode;


    protected Integer isDel = 0; //0:正常 1:删除
    /*
     * 创建人
     */
    protected String creater;

    /*
     * 最后修改时间
     */
    @TableField(value = "last_modify_time",fill = FieldFill.UPDATE)
    protected LocalDateTime lastModifyTime;
    /*
     * 最后修改人
     */
    protected String lastModify;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}