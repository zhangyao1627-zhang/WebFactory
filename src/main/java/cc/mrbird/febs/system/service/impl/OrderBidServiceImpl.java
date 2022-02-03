package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.OrderBid;
import cc.mrbird.febs.system.mapper.OrderBidMapper;
import cc.mrbird.febs.system.service.IOrderBidService;
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
 * @date 2021-07-20 09:29:12
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OrderBidServiceImpl extends ServiceImpl<OrderBidMapper, OrderBid> implements IOrderBidService {

    private final OrderBidMapper orderBidMapper;

    @Override
    public IPage<OrderBid> findOrderBids(QueryRequest request, OrderBid orderBid) {
        LambdaQueryWrapper<OrderBid> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        queryWrapper.eq(OrderBid::getOrderId,orderBid.getOrderId());
        Page<OrderBid> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<OrderBid> findOrderBids(OrderBid orderBid) {
	    LambdaQueryWrapper<OrderBid> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrderBid(OrderBid orderBid) {
        this.save(orderBid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrderBid(OrderBid orderBid) {
        this.saveOrUpdate(orderBid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrderBid(OrderBid orderBid) {
        LambdaQueryWrapper<OrderBid> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wrapper);
	}
}
