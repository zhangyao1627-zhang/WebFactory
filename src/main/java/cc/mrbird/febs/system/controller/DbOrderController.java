package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.system.entity.DbOrder;
import cc.mrbird.febs.system.service.IDbOrderService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
 * @date 2021-07-19 07:07:00
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("order")
public class DbOrderController extends BaseController {


    private final IDbOrderService dbOrderService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "dbOrder")
    public String dbOrderIndex(){
        return FebsUtil.view("dbOrder/dbOrder");
    }

    @GetMapping("dbOrder")
    @ResponseBody
    @RequiresPermissions("dbOrder:list")
    public FebsResponse getAllDbOrders(DbOrder dbOrder) {
        return new FebsResponse().success().data(dbOrderService.findDbOrders(dbOrder));
    }

    @GetMapping("list")
    @ResponseBody
    public FebsResponse dbOrderList(@Param("userId") Long userId, QueryRequest request, DbOrder dbOrder) {
        if (userId != 1){
            System.out.println("get the userId of the list");
            System.out.println(userId);
            System.out.println("Get into userId choice");
            dbOrder.setUserId(userId);
        }
        Map<String, Object> dataTable = getDataTable(this.dbOrderService.findDbOrders(request, dbOrder));
        return new FebsResponse().success().data(dataTable);
    }

    @GetMapping("listGet")
    @ResponseBody
    public FebsResponse dbOrderListGet(QueryRequest request, DbOrder dbOrder) {
        dbOrder.setOrderStatus("1");
        Map<String, Object> dataTable = getDataTable(this.dbOrderService.findDbOrders(request, dbOrder));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增DbOrder", exceptionMessage = "新增DbOrder失败")
    @PostMapping("add")
    @ResponseBody
    public FebsResponse addDbOrder(@Valid DbOrder dbOrder) {
        System.out.println("-----------------here-----------");
        System.out.println("the order that entered here");
        System.out.println(dbOrder);
        dbOrder.setOrderStatus("0");
        this.dbOrderService.createDbOrder(dbOrder);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除DbOrder", exceptionMessage = "删除DbOrder失败")
    @GetMapping("dbOrder/delete")
    @ResponseBody
    @RequiresPermissions("dbOrder:delete")
    public FebsResponse deleteDbOrder(DbOrder dbOrder) {
        this.dbOrderService.deleteDbOrder(dbOrder);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改DbOrder", exceptionMessage = "修改DbOrder失败")
    @PostMapping("update")
    @ResponseBody
    public FebsResponse updateDbOrder(DbOrder dbOrder) {
        System.out.println("decide whether enter the updateorder");
        this.dbOrderService.updateDbOrder(dbOrder);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改DbOrder", exceptionMessage = "导出Excel失败")
    @PostMapping("dbOrder/excel")
    @ResponseBody
    @RequiresPermissions("dbOrder:export")
    public void export(QueryRequest queryRequest, DbOrder dbOrder, HttpServletResponse response) {
        List<DbOrder> dbOrders = this.dbOrderService.findDbOrders(queryRequest, dbOrder).getRecords();
        ExcelKit.$Export(DbOrder.class, response).downXlsx(dbOrders, false);
    }
}
