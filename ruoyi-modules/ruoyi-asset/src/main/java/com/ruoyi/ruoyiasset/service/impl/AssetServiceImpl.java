package com.ruoyi.ruoyiasset.service.impl;

import com.ruoyi.common.redis.service.RedisService;
import com.ruoyi.ruoyiasset.config.ErrorCode;
import com.ruoyi.ruoyiasset.config.RedisCode;
import com.ruoyi.ruoyiasset.domain.Asset;
import com.ruoyi.ruoyiasset.domain.Person;
import com.ruoyi.ruoyiasset.mapper.AssetMapper;
import com.ruoyi.ruoyiasset.mapper.PersonMapper;
import com.ruoyi.ruoyiasset.service.IAssetService;
import com.ruoyi.ruoyiasset.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * assetService业务层处理
 *
 * @author ruoyi
 * @date 2024-06-30
 */
@Service
public class AssetServiceImpl implements IAssetService {
    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    public RedisService redisService;

    @Autowired
    private HttpUtils httpUtils;

    /**
     * 查询asset
     *
     * @param id asset主键
     * @return asset
     */
    @Override
    public Asset selectAssetById(Long id) {
        Asset asset = assetMapper.selectAssetById(id);
        Asset refreshAsset = getSymbol(asset.getSymbol(), false);
        if (refreshAsset != null) {
            asset.setLastPrice(refreshAsset.getLastPrice());
        }
        return asset;
    }

    /**
     * 查询asset列表
     *
     * @param asset asset
     * @return asset
     */
    @Override
    public List<Asset> selectAssetList(Asset asset) {
        List<Asset> list = assetMapper.selectAssetList(asset);

        for (Asset temp : list) {
            Asset refreshAsset = getSymbol(temp.getSymbol(), false);
            if (refreshAsset != null) {
                temp.setLastPrice(refreshAsset.getLastPrice());
            }
        }
        return list;
    }

    /**
     * 新增asset
     *
     * @param asset asset
     * @return 结果
     */
    @Override
    public int insertAsset(Asset asset) {
        Person queryPerson = new Person();
        queryPerson.setAccount(asset.getAccount());
        List<Person> persons = personMapper.selectPersonList(queryPerson);
        //账号不存在
        if (persons == null || persons.isEmpty()) {
            return ErrorCode.ACCOUNT_NO_EXIST;
        }
        Asset queryAsset = new Asset();
        queryAsset.setAccount(asset.getAccount());
        queryAsset.setSymbol(asset.getSymbol());

        List<Asset> assets = assetMapper.selectAssetList(queryAsset);
        //账号加代号已经存在
        if (!assets.isEmpty()) {
            return ErrorCode.ACCOUNT_AND_SYMBOL_EXIST;
        }

        Person person = persons.get(0);
        asset.setPersonId(person.getId());
        asset.setName(person.getName());
        return assetMapper.insertAsset(asset);
    }

    /**
     * 修改asset
     *
     * @param asset asset
     * @return 结果
     */
    @Override
    public int updateAsset(Asset asset) {
        return assetMapper.updateAsset(asset);
    }

    /**
     * 批量删除asset
     *
     * @param ids 需要删除的asset主键
     * @return 结果
     */
    @Override
    public int deleteAssetByIds(Long[] ids) {
        return assetMapper.deleteAssetByIds(ids);
    }

    /**
     * 删除asset信息
     *
     * @param id asset主键
     * @return 结果
     */
    @Override
    public int deleteAssetById(Long id) {
        return assetMapper.deleteAssetById(id);
    }

    /**
     * @param symbol  代号
     * @param refresh 刷新
     * @return 资产
     */
    @Override
    public Asset getSymbol(String symbol, boolean refresh) {
        String key = RedisCode.getAssetKey(symbol);
        Asset asset = redisService.getCacheObject(key);
        //缓存没有获取
        if (asset == null || refresh) {
            asset = httpUtils.sendGetMsg(symbol);
        }
        //缓存非空刷新
        if (asset != null) {
            redisService.setCacheObject(key, asset);
        }
        return asset;
    }

    /**
     * 得到所有代号
     *
     * @return 代号集合
     */
    @Override
    public List<String> selectSymbolList() {
        return assetMapper.selectSymbolList();
    }
}
