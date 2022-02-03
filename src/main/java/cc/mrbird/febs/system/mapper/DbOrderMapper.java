package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.DbOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper
 *
 * @author zhangyao
 * @date 2021-07-19 07:07:00
 */
public interface DbOrderMapper extends BaseMapper<DbOrder> {

    <T> IPage<DbOrder> findDbOrderDetailPage(Page<T> page, @Param("dbOrder") DbOrder dbOrder);

    long countDbOrderDetail(@Param("dbOrder") DbOrder dbOrder);
}
