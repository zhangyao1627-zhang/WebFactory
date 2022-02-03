package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.ControllerEndpoint;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.common.entity.FebsConstant;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.entity.FebsResponse;
import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.OrderBid;
import cc.mrbird.febs.system.service.IOrderBidService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

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
 * @date 2021-07-20 09:29:12
 */
@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("orderBid")
public class OrderBidController extends BaseController {

    private final IOrderBidService orderBidService;

    @GetMapping(FebsConstant.VIEW_PREFIX + "orderBid")
    public String orderBidIndex(){
        return FebsUtil.view("orderBid/orderBid");
    }

    @GetMapping("orderBid")
    @ResponseBody
    @RequiresPermissions("orderBid:list")
    public FebsResponse getAllOrderBids(OrderBid orderBid) {
        return new FebsResponse().success().data(orderBidService.findOrderBids(orderBid));
    }

    @GetMapping("list")
    @ResponseBody
    public FebsResponse orderBidList(@Param("orderId") Long orderId, QueryRequest request, OrderBid orderBid) {
        System.out.println("-----------------------------list");
        System.out.println("------------------------------orderlist");
        System.out.println();
        orderBid.setOrderId(orderId);
        Map<String, Object> dataTable = getDataTable(this.orderBidService.findOrderBids(request, orderBid));
        return new FebsResponse().success().data(dataTable);
    }

    @ControllerEndpoint(operation = "新增OrderBid", exceptionMessage = "新增OrderBid失败")
    @PostMapping("add")
    @ResponseBody
    public FebsResponse addOrderBid(@Valid OrderBid orderBid) {
        this.orderBidService.createOrderBid(orderBid);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "删除OrderBid", exceptionMessage = "删除OrderBid失败")
    @GetMapping("orderBid/delete")
    @ResponseBody
    @RequiresPermissions("orderBid:delete")
    public FebsResponse deleteOrderBid(OrderBid orderBid) {
        this.orderBidService.deleteOrderBid(orderBid);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改OrderBid", exceptionMessage = "修改OrderBid失败")
    @PostMapping("orderBid/update")
    @ResponseBody
    @RequiresPermissions("orderBid:update")
    public FebsResponse updateOrderBid(OrderBid orderBid) {
        this.orderBidService.updateOrderBid(orderBid);
        return new FebsResponse().success();
    }

    @ControllerEndpoint(operation = "修改OrderBid", exceptionMessage = "导出Excel失败")
    @PostMapping("orderBid/excel")
    @ResponseBody
    @RequiresPermissions("orderBid:export")
    public void export(QueryRequest queryRequest, OrderBid orderBid, HttpServletResponse response) {
        List<OrderBid> orderBids = this.orderBidService.findOrderBids(queryRequest, orderBid).getRecords();
        ExcelKit.$Export(OrderBid.class, response).downXlsx(orderBids, false);
    }
}
