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
 * @date 2021-07-15 15:01:20
 */
@Data
@TableName("product_type")
public class Productype {

    /**
     * 
     */
    @TableId(value = "product_type_id", type = IdType.AUTO)
    private String productTypeId;

    /**
     * 
     */
    @TableField("product_type_name")
    private String productTypeName;

    @TableField("product_type_description")
    private String productTypeDescription;
}
