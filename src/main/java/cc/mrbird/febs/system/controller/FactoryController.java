package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Factory;
import cc.mrbird.febs.system.service.IFactoryService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *  Controller
 *
 * @author zhangyao
 * @date 2021-07-14 18:25:37
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("factory")
public class FactoryController extends BaseController {

    private final IFactoryService factoryService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "factory")
    public String factoryIndex(){
        return FebsUtil.view("factory/factory");
    }

    @GetMapping("factory")
    @ResponseBody
    @RequiresPermissions("factory:list")
    public FebsResponse getAllFactorys(Factory factory) {
        return new FebsResponse().success().data(factoryService.findFactorys(factory));
    }

    @GetMapping("list")
//    @RequiresPermissions("factory:list")
    @ResponseBody
    public FebsResponse factoryList(QueryRequest request, Factory factory) {
        System.out.println("At least enter here");
        Map<String, Object> dataTable = getDataTable(this.factoryService.findFactorys(request, factory));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Factory", exceptionMessage = "新增Factory失败")
    @PostMapping("factory")
    @ResponseBody
    @RequiresPermissions("factory:add")
    public FebsResponse addFactory(@Valid Factory factory) {
        this.factoryService.createFactory(factory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Factory", exceptionMessage = "删除Factory失败")
    @GetMapping("factory/delete")
    @ResponseBody
    @RequiresPermissions("factory:delete")
    public FebsResponse deleteFactory(Factory factory) {
        this.factoryService.deleteFactory(factory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Factory", exceptionMessage = "修改Factory失败")
    @PostMapping("update")
    @ResponseBody
//    @RequiresPermissions("factory:update")
    public FebsResponse updateFactory(Factory factory) {
        System.out.println("Get into the factoryupdatefield");
        System.out.println(factory.getFactoryName());
        System.out.println(factory.getFactoryStatus());
        this.factoryService.updateFactory(factory);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Factory", exceptionMessage = "导出Excel失败")
    @PostMapping("factory/excel")
    @ResponseBody
    @RequiresPermissions("factory:export")
    public void export(QueryRequest queryRequest, Factory factory, HttpServletResponse response) {
        List<Factory> factorys = this.factoryService.findFactorys(queryRequest, factory).getRecords();
        ExcelKit.$Export(Factory.class, response).downXlsx(factorys, false);
    }
}
