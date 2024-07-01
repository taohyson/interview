package com.ruoyi.ruoyiasset.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.ruoyiasset.config.ErrorCode;
import com.ruoyi.ruoyiasset.domain.Asset;
import com.ruoyi.ruoyiasset.service.IAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * assetController
 *
 * @author ruoyi
 * @date 2024-06-30
 */
@RestController
@RequestMapping("/asset")
public class AssetController extends BaseController {
    @Autowired
    private IAssetService assetService;

    /**
     * 查询asset列表
     */
    @RequiresPermissions("asset:asset:list")
    @GetMapping("/list")
    public TableDataInfo list(Asset asset) {
        startPage();
        List<Asset> list = assetService.selectAssetList(asset);
        return getDataTable(list);
    }

    /**
     * 导出asset列表
     */
    @RequiresPermissions("asset:asset:export")
    @Log(title = "asset", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Asset asset) {
        List<Asset> list = assetService.selectAssetList(asset);
        ExcelUtil<Asset> util = new ExcelUtil<Asset>(Asset.class);
        util.exportExcel(response, list, "asset数据");
    }

    /**
     * 获取asset详细信息
     */
    @RequiresPermissions("asset:asset:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(assetService.selectAssetById(id));
    }

    /**
     * 新增asset
     */
    @RequiresPermissions("asset:asset:add")
    @Log(title = "asset", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Asset asset) {
        int result = assetService.insertAsset(asset);
        if (result == ErrorCode.ACCOUNT_NO_EXIST) {
            return AjaxResult.error("账号不存在");
        }
        if (result == ErrorCode.ACCOUNT_AND_SYMBOL_EXIST) {
            return AjaxResult.error("账号加代号已经存在");
        }
        return AjaxResult.success();
    }

    /**
     * 修改asset
     */
    @RequiresPermissions("asset:asset:edit")
    @Log(title = "asset", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Asset asset) {
        return toAjax(assetService.updateAsset(asset));
    }

    /**
     * 删除asset
     */
    @RequiresPermissions("asset:asset:remove")
    @Log(title = "asset", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(assetService.deleteAssetByIds(ids));
    }


    /**
     * 查询代号
     */
    @GetMapping("/querySymbol")
    public AjaxResult querySymbol(Asset asset) {
        Asset result = assetService.getSymbol(asset.getSymbol(),false);
        if (result == null) {
            return AjaxResult.error("请输入正确代号");
        }
        return success(result);
    }
}
