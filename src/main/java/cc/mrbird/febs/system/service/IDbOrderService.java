package cc.mrbird.febs.system.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.DbOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author zhangyao
 * @date 2021-07-19 07:07:00
 */
public interface IDbOrderService extends IService<DbOrder> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param dbOrder dbOrder
     * @return IPage<DbOrder>
     */
    IPage<DbOrder> findDbOrders(QueryRequest request, DbOrder dbOrder);

    /**
     * 查询（所有）
     *
     * @param dbOrder dbOrder
     * @return List<DbOrder>
     */
    List<DbOrder> findDbOrders(DbOrder dbOrder);

    /**
     * 新增
     *
     * @param dbOrder dbOrder
     */
    void createDbOrder(DbOrder dbOrder);

    /**
     * 修改
     *
     * @param dbOrder dbOrder
     */
    void updateDbOrder(DbOrder dbOrder);

    /**
     * 删除
     *
     * @param dbOrder dbOrder
     */
    void deleteDbOrder(DbOrder dbOrder);
}
