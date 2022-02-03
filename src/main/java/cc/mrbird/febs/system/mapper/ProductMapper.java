package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.Product;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author zhangyao
 * @date 2021-07-16 18:11:20
 */
public interface ProductMapper extends BaseMapper<Product> {

    Product findByProductId(Long productId);

    <T> IPage<Product> findProductDetailPage(Page<T> page, @Param("product") Product product);

    long countProductDetail(@Param("product") Product product);

    List<Product> findProductDetail(@Param("product") Product product);

}
