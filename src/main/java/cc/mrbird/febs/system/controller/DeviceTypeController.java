package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.DeviceType;
import cc.mrbird.febs.system.service.IDeviceTypeService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

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
 * @date 2021-07-17 21:26:05
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("deviceType")
public class DeviceTypeController extends BaseController {

    private final IDeviceTypeService deviceTypeService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "deviceType")
    public String deviceTypeIndex(){
        return FebsUtil.view("deviceType/deviceType");
    }

    @GetMapping("deviceType")
    @ResponseBody
    @RequiresPermissions("deviceType:list")
    public FebsResponse getAllDeviceTypes(DeviceType deviceType) {
        return new FebsResponse().success().data(deviceTypeService.findDeviceTypes(deviceType));
    }

    @GetMapping("list")
    @ResponseBody
    public FebsResponse deviceTypeList(QueryRequest request, DeviceType deviceType) {
        Map<String, Object> dataTable = getDataTable(this.deviceTypeService.findDeviceTypes(request, deviceType));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增DeviceType", exceptionMessage = "新增DeviceType失败")
    @PostMapping("add")
    @ResponseBody
    public FebsResponse addDeviceType(@Valid DeviceType deviceType) {
        this.deviceTypeService.createDeviceType(deviceType);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除DeviceType", exceptionMessage = "删除DeviceType失败")
    @GetMapping("delete/{deviceTypeId}")
    @ResponseBody

    public FebsResponse deleteDeviceType(@PathVariable Long deviceTypeId) {
        DeviceType deviceType = deviceTypeService.findById(deviceTypeId);
        this.deviceTypeService.deleteDeviceType(deviceType);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改DeviceType", exceptionMessage = "修改DeviceType失败")
    @PostMapping("update")
    @ResponseBody
    public FebsResponse updateDeviceType(DeviceType deviceType) {
        this.deviceTypeService.updateDeviceType(deviceType);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改DeviceType", exceptionMessage = "导出Excel失败")
    @PostMapping("deviceType/excel")
    @ResponseBody
    public void export(QueryRequest queryRequest, DeviceType deviceType, HttpServletResponse response) {
        List<DeviceType> deviceTypes = this.deviceTypeService.findDeviceTypes(queryRequest, deviceType).getRecords();
        ExcelKit.$Export(DeviceType.class, response).downXlsx(deviceTypes, false);
    }
}
