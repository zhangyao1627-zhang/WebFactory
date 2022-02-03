package cc.mrbird.febs.system.service;

import cc.mrbird.febs.system.entity.Factory;
import cc.mrbird.febs.system.entity.Productype;

import cc.mrbird.febs.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author zhangyao
 * @date 2021-07-15 15:01:20
 */
public interface IProductypeService extends IService<Productype> {

    //通过id来查询对应的产品类型：
     Productype findById(Long productTypeId);
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param productype productype
     * @return IPage<Productype>
     */
    IPage<Productype> findProductypes(QueryRequest request, Productype productype);

    /**
     * 查询（所有）
     *
     * @param productype productype
     * @return List<Productype>
     */
    List<Productype> findProductypes(Productype productype);

    /**
     * 新增
     *
     * @param productype productype
     */
    void createProductype(Productype productype);

    /**
     * 修改
     *
     * @param productype productype
     */
    void updateProductype(Productype productype);

    /**
     * 删除
     *
     * @param productype productype
     */
    void deleteProductype(Productype productype);
}
