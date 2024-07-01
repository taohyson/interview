package com.ruoyi.ruoyiasset.config;

/**
 * redis编码
 */
public class RedisCode {

    //资产前缀
    public static String ASSET_PRE ="ASSET_";

    /**
     * 得到资产key
     * @param symbol 代号
     * @return 资产key
     */
    public static String getAssetKey(String symbol){
        return RedisCode.ASSET_PRE + symbol;
    }


}
