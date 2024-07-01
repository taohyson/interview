package com.ruoyi.ruoyiasset.service;

import java.util.List;
import com.ruoyi.ruoyiasset.domain.Asset;

/**
 * assetService接口
 * 
 * @author ruoyi
 * @date 2024-06-30
 */
public interface IAssetService 
{
    /**
     * 查询asset
     * 
     * @param id asset主键
     * @return asset
     */
    public Asset selectAssetById(Long id);

    /**
     * 查询asset列表
     * 
     * @param asset asset
     * @return asset集合
     */
    public List<Asset> selectAssetList(Asset asset);

    /**
     * 新增asset
     * 
     * @param asset asset
     * @return 结果
     */
    public int insertAsset(Asset asset);

    /**
     * 修改asset
     * 
     * @param asset asset
     * @return 结果
     */
    public int updateAsset(Asset asset);

    /**
     * 批量删除asset
     * 
     * @param ids 需要删除的asset主键集合
     * @return 结果
     */
    public int deleteAssetByIds(Long[] ids);

    /**
     * 删除asset信息
     * 
     * @param id asset主键
     * @return 结果
     */
    public int deleteAssetById(Long id);

    /**
     * 得到资产根据代号
     * @param symbol 代号
     * @param refresh 帅新
     * @return
     */
    public Asset getSymbol(String symbol,boolean refresh);



    /**
     * 得到所有代号
     *
     * @return 代号
     */
    public List<String> selectSymbolList();
}
