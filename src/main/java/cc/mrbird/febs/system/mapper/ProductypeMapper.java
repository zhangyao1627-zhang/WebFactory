package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.Productype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper
 *
 * @author zhangyao
 * @date 2021-07-15 15:01:20
 */
public interface ProductypeMapper extends BaseMapper<Productype> {

    Productype findById(Long productTypeId);

    long countProductTypeDetail(@Param("productype") Productype productype);
}
