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
 * @date 2021-07-16 18:11:20
 */
@Data
@TableName("product")
public class Product {

    /**
     * 
     */
    @TableField("product_describe")
    private String productDescribe;

    /**
     * 
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;

    /**
     * 
     */
    @TableField("product_name")
    private String productName;

    /**
     * 
     */
    @TableField("product_norms")
    private String productNorms;

    /**
     * 
     */
    @TableField("product_number")
    private String productNumber;

    /**
     * 
     */
    @TableField("product_type_id")
    private String productTypeId;

    @TableField(exist = false)
    private String productTypeName;

}
