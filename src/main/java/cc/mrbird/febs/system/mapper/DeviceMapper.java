package cc.mrbird.febs.system.mapper;

import cc.mrbird.febs.system.entity.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper
 *
 * @author zhangyao
 * @date 2021-07-18 08:33:30
 */
public interface DeviceMapper extends BaseMapper<Device> {

    Device findById(Long deviceId);

    <T> IPage<Device> findDeviceDetailPage(Page<T> page, @Param("device") Device device);

    long countDeviceDetail(@Param("device") Device device);

    List<Device> findDeviceDetail(@Param("device") Device device);


}
