package cc.mrbird.febs.system.entity;


import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 *  Entity
 *
 * @author zhangyao
 * @date 2021-07-14 18:25:37
 */
@Data
@TableName("t_factory")
public class Factory {

    /**
     * 
     */
    @TableField("FACTORY_DETAIL")
    private String factoryDetail;

    /**
     * 
     */
    @TableId(value = "FACTORY_ID", type = IdType.AUTO)
    private Long factoryId;

    /**
     * 
     */
    @TableField("FACTORY_NAME")
    private String factoryName;

    /**
     * 
     */
    @TableField("FACTORY_STATUS")
    private String factoryStatus;

    /**
     * 
     */
    @TableField("USER_ID")
    private Long userId;

}
