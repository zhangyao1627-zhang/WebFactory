package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.DbOrder;
import cc.mrbird.febs.system.mapper.DbOrderMapper;
import cc.mrbird.febs.system.service.IDbOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Service实现
 *
 * @author zhangyao
 * @date 2021-07-19 07:07:00
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DbOrderServiceImpl extends ServiceImpl<DbOrderMapper, DbOrder> implements IDbOrderService {

    private final DbOrderMapper dbOrderMapper;

    @Override
    public IPage<DbOrder> findDbOrders(QueryRequest request, DbOrder dbOrder) {
        LambdaQueryWrapper<DbOrder> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<DbOrder> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countDbOrderDetail(dbOrder));
        return baseMapper.findDbOrderDetailPage(page,dbOrder);

    }

    @Override
    public List<DbOrder> findDbOrders(DbOrder dbOrder) {
	    LambdaQueryWrapper<DbOrder> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDbOrder(DbOrder dbOrder) {
        this.save(dbOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDbOrder(DbOrder dbOrder) {
        this.saveOrUpdate(dbOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDbOrder(DbOrder dbOrder) {
        LambdaQueryWrapper<DbOrder> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
