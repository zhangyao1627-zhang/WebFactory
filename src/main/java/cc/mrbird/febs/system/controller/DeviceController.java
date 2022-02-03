package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.system.entity.Device;
import cc.mrbird.febs.system.service.IDeviceService;
import cc.mrbird.febs.system.service.IFactoryService;
import cc.mrbird.febs.system.service.IUserService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author zhangyao
 * @date 2021-07-18 08:33:30
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("device")
public class DeviceController extends BaseController {

    private final IUserService userService;
    private final IFactoryService factoryService;
    private final IDeviceService deviceService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "device")
    public String deviceIndex(){
        return FebsUtil.view("device/device");
    }

    @GetMapping("device")
    @ResponseBody
    @RequiresPermissions("device:list")
    public FebsResponse getAllDevices(Device device) {
        return new FebsResponse().success().data(deviceService.findDevices(device));
    }

    @GetMapping("list")
    @ResponseBody
    public FebsResponse deviceList(@Param("userId") Long userId, QueryRequest request, Device device) {
        if (userId != 1){
            System.out.println("Get into userId choice");
            device.setUserId(userId);
        }
        System.out.println(userId);
        Map<String, Object> dataTable = getDataTable(this.deviceService.findDevices(request, device));
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("listRent")
    @ResponseBody
    public FebsResponse deviceRentList(QueryRequest request, Device device) {
        device.setDeviceRentStatus("2");
        device.setDeviceRunningStatus("0");
        Map<String, Object> dataTable = getDataTable(this.deviceService.findDevices(request, device));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Device", exceptionMessage = "新增Device失败")
    @PostMapping("add")
    @ResponseBody
    public FebsResponse addDevice(@Valid Device device) {

        device.setFactoryId(userService.findByName(device.getUserName()).getFactoryId());
        device.setDeviceRunningStatus("0");
        device.setDeviceRentStatus("2");
        this.deviceService.createDevice(device);
        return new FebsResponse().success();
    }
    @ControllerEndpoint(operation = "新增Device", exceptionMessage = "新增Device失败")
    @PostMapping("addFactoryDevice")
    @ResponseBody
    public FebsResponse addFactoryDevice(@Valid Device device) {
        System.out.println("enter here to add factorydevice");
        System.out.println(device);
        device.setFactoryId(userService.findByName(device.getUserName()).getFactoryId());
        System.out.println(userService.findByName(device.getUserName()));
        System.out.println(userService.findByName(device.getUserName()).getFactoryId());
        device.setDeviceRunningStatus("0");
        device.setDeviceRentStatus("0");
        this.deviceService.createDevice(device);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Device", exceptionMessage = "删除Device失败")
    @GetMapping("delete/{deviceId}")
    @ResponseBody
    public FebsResponse deleteDevice(@PathVariable Long deviceId) {
        Device device = deviceService.findById(deviceId);
        this.deviceService.deleteDevice(device);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Device", exceptionMessage = "修改Device失败")
    @PostMapping("update")
    @ResponseBody
    public FebsResponse updateDevice(Device device) {
        System.out.println("---------------------------out-----------------");
        System.out.println(device);
//        device.setFactoryId();
        this.deviceService.updateDevice(device);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Device", exceptionMessage = "导出Excel失败")
    @PostMapping("device/excel")
    @ResponseBody
    @RequiresPermissions("device:export")
    public void export(QueryRequest queryRequest, Device device, HttpServletResponse response) {
        List<Device> devices = this.deviceService.findDevices(queryRequest, device).getRecords();
        ExcelKit.$Export(Device.class, response).downXlsx(devices, false);
    }
}
