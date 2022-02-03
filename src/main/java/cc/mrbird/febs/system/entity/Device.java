package cc.mrbird.febs.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *  Entity
 *
 * @author zhangyao
 * @date 2021-07-18 08:33:30
 */
@Data
@TableName("device")
public class Device {

    /**
     * 描述
     */
    @TableField("device_describe")
    private String deviceDescribe;

    /**
     * 
     */
    @TableId(value = "device_id", type = IdType.AUTO)
    private Long deviceId;

    /**
     * 设备名称
     */
    @TableField("device_name")
    private String deviceName;

    /**
     * 规格
     */
    @TableField("device_norms")
    private String deviceNorms;

    /**
     * 设备编号
     */
    @TableField("device_number")
    private String deviceNumber;

    /**
     * 租用状态
     */
    @TableField("device_rent_status")
    private String deviceRentStatus;

    /**
     * 设备状态
     */
    @TableField("device_running_status")
    private String deviceRunningStatus;

    /**
     * 类型id
     */
    @TableField("device_type_id")
    private Long deviceTypeId;

    /**
     * 设备所属工厂
     */
    @TableField("factory_id")
    private Long factoryId;

    @TableField(exist = false)
    private String factoryName;

    @TableField(exist = false)
    private String deviceTypeName;

     @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private Long userId;

}
