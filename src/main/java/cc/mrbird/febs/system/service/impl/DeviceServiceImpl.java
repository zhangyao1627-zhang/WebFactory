package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Device;
import cc.mrbird.febs.system.mapper.DeviceMapper;
import cc.mrbird.febs.system.service.IDeviceService;
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
 * @date 2021-07-18 08:33:30
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    private final DeviceMapper deviceMapper;

    @Override
    public Device findById(Long deviceId){
        return deviceMapper.findById(deviceId);
    }


    @Override
    public IPage<Device> findDevices(QueryRequest request, Device device) {
        LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<Device> page = new Page<>(request.getPageNum(), request.getPageSize());
        page.setSearchCount(false);
        page.setTotal(baseMapper.countDeviceDetail(device));
        System.out.println(baseMapper.countDeviceDetail(device));
        return baseMapper.findDeviceDetailPage(page,device);
    }

    @Override
    public List<Device> findDevices(Device device) {
	    LambdaQueryWrapper<Device> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDevice(Device device) {
        this.save(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDevice(Device device) {
        this.saveOrUpdate(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDevice(Device device) {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
        wrapper.eq(Device::getDeviceId,device.getDeviceId());
	    this.remove(wrapper);
	}
}
