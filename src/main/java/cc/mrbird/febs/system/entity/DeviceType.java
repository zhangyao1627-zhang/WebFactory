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
 * @date 2021-07-17 22:00:35
 */
@Data
@TableName("device_type")
public class DeviceType {

    /**
     * 
     */
    @TableField("device_type_detail")
    private String deviceTypeDetail;

    /**
     * 
     */
    @TableId(value = "device_type_id", type = IdType.AUTO)
    private Long deviceTypeId;

    /**
     * 
     */
    @TableField("device_type_name")
    private String deviceTypeName;

}
