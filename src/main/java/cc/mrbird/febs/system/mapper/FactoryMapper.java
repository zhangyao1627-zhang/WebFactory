package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.Factory;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper
 *
 * @author zhangyao
 * @date 2021-07-14 18:25:37
 */
public interface FactoryMapper extends BaseMapper<Factory> {

    Factory findByName(String factoryname);
}
