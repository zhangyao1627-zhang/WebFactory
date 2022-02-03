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
 * @date 2021-07-20 09:29:12
 */
@Data
@TableName("order_bid")
public class OrderBid {

    /**
     * 
     */
    @TableId(value = "order_bid_id", type = IdType.AUTO)
    private Long orderBidId;

    /**
     * 
     */
    @TableField("order_id")
    private Long orderId;

    /**
     * 
     */
    @TableField("factory_id")
    private Long factoryId;

    /**
     * 
     */
    @TableField("order_bidprice")
    private String orderBidprice;

}
