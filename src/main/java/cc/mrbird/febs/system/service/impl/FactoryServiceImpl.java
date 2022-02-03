package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Factory;
import cc.mrbird.febs.system.entity.User;
import cc.mrbird.febs.system.mapper.FactoryMapper;
import cc.mrbird.febs.system.service.IFactoryService;
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
 * @date 2021-07-14 18:25:37
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FactoryServiceImpl extends ServiceImpl<FactoryMapper, Factory> implements IFactoryService {

    private final FactoryMapper factoryMapper;

    @Override
    public Factory findByName(String factoryname) {
        return factoryMapper.findByName(factoryname);
    }

    @Override
    public IPage<Factory> findFactorys(QueryRequest request, Factory factory) {
        LambdaQueryWrapper<Factory> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Factory> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<Factory> findFactorys(Factory factory) {
	    LambdaQueryWrapper<Factory> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createFactory(Factory factory) {
        this.save(factory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFactory(Factory factory) {
        this.saveOrUpdate(factory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFactory(Factory factory) {
        LambdaQueryWrapper<Factory> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
