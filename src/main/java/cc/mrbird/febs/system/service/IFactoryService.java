package cc.mrbird.febs.system.service;

import cc.mrbird.febs.system.entity.Factory;

import cc.mrbird.febs.common.entity.QueryRequest;
import cc.mrbird.febs.system.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author zhangyao
 * @date 2021-07-14 18:25:37
 */
public interface IFactoryService extends IService<Factory> {


    //根据工厂名称来查询工厂的所有属性
    Factory findByName(String factoryname);
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param factory factory
     * @return IPage<Factory>
     */
    IPage<Factory> findFactorys(QueryRequest request, Factory factory);

    /**
     * 查询（所有）
     *
     * @param factory factory
     * @return List<Factory>
     */
    List<Factory> findFactorys(Factory factory);

    /**
     * 新增
     *
     * @param factory factory
     */
    void createFactory(Factory factory);

    /**
     * 修改
     *
     * @param factory factory
     */
    void updateFactory(Factory factory);

    /**
     * 删除
     *
     * @param factory factory
     */
    void deleteFactory(Factory factory);
}
