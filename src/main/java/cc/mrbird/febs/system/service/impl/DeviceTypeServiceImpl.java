package cc.mrbird.febs.system.service.impl;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.DeviceType;
import cc.mrbird.febs.system.entity.DeviceType;
import cc.mrbird.febs.system.mapper.DeviceTypeMapper;
import cc.mrbird.febs.system.service.IDeviceTypeService;
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
 * @date 2021-07-17 21:26:05
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeviceTypeServiceImpl extends ServiceImpl<DeviceTypeMapper, DeviceType> implements IDeviceTypeService {

    private final DeviceTypeMapper deviceTypeMapper;

    @Override
    public DeviceType findById(Long deviceTypeId) {
        return deviceTypeMapper.findById(deviceTypeId);
    }

    @Override
    public IPage<DeviceType> findDeviceTypes(QueryRequest request, DeviceType deviceType) {
        LambdaQueryWrapper<DeviceType> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<DeviceType> page = new Page<>(request.getPageNum(), request.getPageSize());
        //设置相应字符串的查询：
        if((deviceType.getDeviceTypeName()!=null)&&(!deviceType.getDeviceTypeName().isEmpty())){
            queryWrapper.eq(DeviceType::getDeviceTypeName,deviceType.getDeviceTypeName());
        }
        if((deviceType.getDeviceTypeDetail()!=null)&&(!deviceType.getDeviceTypeDetail().isEmpty())){
            queryWrapper.eq(DeviceType::getDeviceTypeDetail,deviceType.getDeviceTypeDetail());
        }

        return this.page(page, queryWrapper);
    }

    @Override
    public List<DeviceType> findDeviceTypes(DeviceType deviceType) {
        LambdaQueryWrapper<DeviceType> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDeviceType(DeviceType deviceType) {
        this.save(deviceType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDeviceType(DeviceType deviceType) {
        this.saveOrUpdate(deviceType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDeviceType(DeviceType deviceType) {
        LambdaQueryWrapper<DeviceType> wrapper = new LambdaQueryWrapper<>();
        // TODO 设置删除条件
        wrapper.eq(DeviceType::getDeviceTypeId,deviceType.getDeviceTypeId());
        this.remove(wrapper);
    }
}
