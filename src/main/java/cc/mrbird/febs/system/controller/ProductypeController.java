package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.Productype;
import cc.mrbird.febs.system.service.IProductypeService;
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
 * @date 2021-07-15 15:01:20
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("productType")
public class ProductypeController extends BaseController {

    private final IProductypeService productypeService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "productype")
    public String productypeIndex(){
        return FebsUtil.view("productype/productype");
    }

    @GetMapping("productype")
    @ResponseBody
    @RequiresPermissions("productype:list")
    public FebsResponse getAllProductypes(Productype productype) {
        return new FebsResponse().success().data(productypeService.findProductypes(productype));
    }

    @GetMapping("list")
    @ResponseBody
//    @RequiresPermissions("productype:list")
    public FebsResponse productypeList(QueryRequest request, Productype productype) {

        Map<String, Object> dataTable = getDataTable(this.productypeService.findProductypes(request, productype));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增Productype", exceptionMessage = "新增Productype失败")
    @PostMapping("add")
    @ResponseBody
//    @RequiresPermissions("productype:add")
    public FebsResponse addProductype(@Valid Productype productype) {
        System.out.println("enter into the productType");
        System.out.println("---------------------------------");
        System.out.println(productype.getProductTypeName());
        System.out.println(productype.getProductTypeDescription());
        System.out.println(productype);
        System.out.println("---------------------------------");
        this.productypeService.createProductype(productype);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除Productype", exceptionMessage = "删除Productype失败")
    @GetMapping("delete/{productTypeId}")
    @ResponseBody

    public FebsResponse deleteProductype(@PathVariable Long productTypeId) {
        Productype productype = productypeService.findById(productTypeId);
        this.productypeService.deleteProductype(productype);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Productype", exceptionMessage = "修改Productype失败")
    @PostMapping("update")
    @ResponseBody
    public FebsResponse updateProductype(Productype productype) {
        this.productypeService.updateProductype(productype);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改Productype", exceptionMessage = "导出Excel失败")
    @PostMapping("productype/excel")
    @ResponseBody
    @RequiresPermissions("productype:export")
    public void export(QueryRequest queryRequest, Productype productype, HttpServletResponse response) {
        List<Productype> productypes = this.productypeService.findProductypes(queryRequest, productype).getRecords();
        ExcelKit.$Export(Productype.class, response).downXlsx(productypes, false);
    }
}
