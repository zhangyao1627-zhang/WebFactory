package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Product;
import cc.mrbird.febs.system.entity.Productype;
import cc.mrbird.febs.system.mapper.ProductMapper;
import cc.mrbird.febs.system.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import lombok.RequiredArgsConstructor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author zhangyao
 * @date 2021-07-16 18:11:20
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    private final ProductMapper productMapper;

    @Override
    public Product findById(Long productTypeId) {
        return productMapper.findByProductId(productTypeId);
    }
    @Override
    public IPage<Product> findProducts(QueryRequest request, Product product) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Product> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countProductDetail(product));
        System.out.println(baseMapper.countProductDetail(product));
        return baseMapper.findProductDetailPage(page,product);
    }

    @Override
    public List<Product> findProducts(Product product) {
	    LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProduct(Product product) {
        System.out.println("2.come to here,and show the product");
        System.out.println(product);
        this.save(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(Product product) {
        this.saveOrUpdate(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Product product) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
        wrapper.eq(Product::getProductId,product.getProductId());
	    this.remove(wrapper);
	}
}
