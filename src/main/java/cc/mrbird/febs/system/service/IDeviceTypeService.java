package cc.mrbird.febs.system.service;

import cc.mrbird.febs.system.entity.DeviceType;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Productype;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author zhangyao
 * @date 2021-07-17 21:26:05
 */
public interface IDeviceTypeService extends IService<DeviceType> {

    DeviceType findById(Long deviceTypeId);
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param deviceType deviceType
     * @return IPage<DeviceType>
     */
    IPage<DeviceType> findDeviceTypes(QueryRequest request, DeviceType deviceType);

    /**
     * 查询（所有）
     *
     * @param deviceType deviceType
     * @return List<DeviceType>
     */
    List<DeviceType> findDeviceTypes(DeviceType deviceType);

    /**
     * 新增
     *
     * @param deviceType deviceType
     */
    void createDeviceType(DeviceType deviceType);

    /**
     * 修改
     *
     * @param deviceType deviceType
     */
    void updateDeviceType(DeviceType deviceType);

    /**
     * 删除
     *
     * @param deviceType deviceType
     */
    void deleteDeviceType(DeviceType deviceType);
}
