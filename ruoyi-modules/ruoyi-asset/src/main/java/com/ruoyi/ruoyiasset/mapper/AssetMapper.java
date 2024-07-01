package com.ruoyi.ruoyiasset.mapper;

import com.ruoyi.ruoyiasset.domain.Asset;

import java.util.List;

/**
 * assetMapper接口
 *
 * @author ruoyi
 * @date 2024-06-30
 */
public interface AssetMapper {
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
     * 得到所有代号
     *
     * @return 代号
     */
    public List<String> selectSymbolList();

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
     * 删除asset
     *
     * @param id asset主键
     * @return 结果
     */
    public int deleteAssetById(Long id);

    /**
     * 批量删除asset
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssetByIds(Long[] ids);
}
