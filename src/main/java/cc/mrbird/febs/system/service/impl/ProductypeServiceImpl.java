package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Factory;
import cc.mrbird.febs.system.entity.Productype;
import cc.mrbird.febs.system.mapper.FactoryMapper;
import cc.mrbird.febs.system.mapper.ProductypeMapper;
import cc.mrbird.febs.system.service.IProductypeService;
import net.sf.ehcache.transaction.xa.commands.StorePutCommand;
import org.apache.poi.util.StringUtil;
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
 * @date 2021-07-15 15:01:20
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductypeServiceImpl extends ServiceImpl<ProductypeMapper, Productype> implements IProductypeService {

    private final ProductypeMapper productypeMapper;

    @Override
    public Productype findById(Long productTypeId) {
        return productypeMapper.findById(productTypeId);
    }

    @Override
    public IPage<Productype> findProductypes(QueryRequest request, Productype productype) {
        LambdaQueryWrapper<Productype> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Productype> page = new Page<>(request.getPageNum(), request.getPageSize());
        //设置相应字符串的查询：
        System.out.println(productype);
        System.out.println(productype.getProductTypeName());
            if((productype.getProductTypeName()!=null)&&(!productype.getProductTypeName().isEmpty())){
                System.out.println("-----------try to get name--------------");
                System.out.println(productype.getProductTypeName());
                queryWrapper.eq(Productype::getProductTypeName,productype.getProductTypeName());
            }
            if((productype.getProductTypeDescription()!=null)&&(!productype.getProductTypeDescription().isEmpty())){
                queryWrapper.eq(Productype::getProductTypeDescription,productype.getProductTypeDescription());
            }

        return this.page(page, queryWrapper);
    }

    @Override
    public List<Productype> findProductypes(Productype productype) {
	    LambdaQueryWrapper<Productype> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createProductype(Productype productype) {
        this.save(productype);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductype(Productype productype) {
        this.saveOrUpdate(productype);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProductype(Productype productype) {
        LambdaQueryWrapper<Productype> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
        wrapper.eq(Productype::getProductTypeId,productype.getProductTypeId());
        this.remove(wrapper);
	}
}
