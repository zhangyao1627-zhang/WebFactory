package cc.mrbird.febs.system.service;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Device;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author zhangyao
 * @date 2021-07-18 08:33:30
 */
public interface IDeviceService extends IService<Device> {
    Device findById(Long deviceId);
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param device device
     * @return IPage<Device>
     */
    IPage<Device> findDevices(QueryRequest request, Device device);

    /**
     * 查询（所有）
     *
     * @param device device
     * @return List<Device>
     */
    List<Device> findDevices(Device device);

    /**
     * 新增
     *
     * @param device device
     */
    void createDevice(Device device);

    /**
     * 修改
     *
     * @param device device
     */
    void updateDevice(Device device);

    /**
     * 删除
     *
     * @param device device
     */
    void deleteDevice(Device device);
}
