package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.DeviceType;
import cc.mrbird.febs.system.entity.Productype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 *  Mapper
 *
 * @author zhangyao
 * @date 2021-07-17 21:26:05
 */
public interface DeviceTypeMapper extends BaseMapper<DeviceType> {
    DeviceType findById(Long deviceTypeId);

    long countDeviceTypeDetail(@Param("deviceType") DeviceType deviceType);
}
