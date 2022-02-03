package cc.mrbird.febs.system.service;

import cc.mrbird.febs.system.entity.Product;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author zhangyao
 * @date 2021-07-16 18:11:20
 */
public interface IProductService extends IService<Product> {
    Product findById(Long productTypeId);
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param product product
     * @return IPage<Product>
     */
    IPage<Product> findProducts(QueryRequest request, Product product);

    /**
     * 查询（所有）
     *
     * @param product product
     * @return List<Product>
     */
    List<Product> findProducts(Product product);

    /**
     * 新增
     *
     * @param product product
     */
    void createProduct(Product product);

    /**
     * 修改
     *
     * @param product product
     */
    void updateProduct(Product product);

    /**
     * 删除
     *
     * @param product product
     */
    void deleteProduct(Product product);
}
