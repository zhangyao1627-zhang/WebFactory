package cc.mrbird.febs.system.service;

import cc.mrbird.febs.system.entity.OrderBid;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author zhangyao
 * @date 2021-07-20 09:29:12
 */
public interface IOrderBidService extends IService<OrderBid> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param orderBid orderBid
     * @return IPage<OrderBid>
     */
    IPage<OrderBid> findOrderBids(QueryRequest request, OrderBid orderBid);

    /**
     * 查询（所有）
     *
     * @param orderBid orderBid
     * @return List<OrderBid>
     */
    List<OrderBid> findOrderBids(OrderBid orderBid);

    /**
     * 新增
     *
     * @param orderBid orderBid
     */
    void createOrderBid(OrderBid orderBid);

    /**
     * 修改
     *
     * @param orderBid orderBid
     */
    void updateOrderBid(OrderBid orderBid);

    /**
     * 删除
     *
     * @param orderBid orderBid
     */
    void deleteOrderBid(OrderBid orderBid);
}
