package cc.mrbird.febs.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *  Entity
 *
 * @author zhangyao
 * @date 2021-07-19 07:07:00
 */
@Data
@TableName("db_order")
public class DbOrder {

    /**
     * 
     */
    @TableField("factory_id")
    private Long factoryId;

    /**
     * 
     */
    @TableField("order_countnum")
    private String orderCountNum;

    /**
     * 
     */
    @TableField("order_customer_address")
    private String orderCustomerAddress;

    /**
     * 
     */
    @TableField("order_customer_name")
    private String orderCustomerName;

    /**
     * 
     */
    @TableField("order_customer_phone")
    private String orderCustomerPhone;

    /**
     * 
     */
    @TableField("order_deaddate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDeadDate;

    /**
     * 
     */
    @TableField("order_deliverDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDeliverDate;

    /**
     * 
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    /**
     * 
     */
    @TableField("order_number")
    private String orderNumber;

    /**
     * 
     */
    @TableField("order_status")
    private String orderStatus;

    /**
     * 
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 
     */
    @TableField("user_id")
    private Long userId;

    @TableField(exist = false)
    private String productName;

}
