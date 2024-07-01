package com.ruoyi.ruoyiasset.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.ruoyiassert.service.IPersonService;
import com.ruoyi.ruoyiasset.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * personController
 *
 * @author ruoyi
 * @date 2024-06-30
 */
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {
    @Autowired
    private IPersonService personService;

    /**
     * 查询person列表
     */
    @RequiresPermissions("assert:person:list")
    @GetMapping("/list")
    public TableDataInfo list(Person person) {
        startPage();
        List<Person> list = personService.selectPersonList(person);
        return getDataTable(list);
    }

    /**
     * 导出person列表
     */
    @RequiresPermissions("assert:person:export")
    @Log(title = "person", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Person person) {
        List<Person> list = personService.selectPersonList(person);
        ExcelUtil<Person> util = new ExcelUtil<Person>(Person.class);
        util.exportExcel(response, list, "person数据");
    }

    /**
     * 获取person详细信息
     */
    @RequiresPermissions("assert:person:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(personService.selectPersonById(id));
    }

    /**
     * 新增person
     */
    @RequiresPermissions("assert:person:add")
    @Log(title = "person", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Person person) {
        return toAjax(personService.insertPerson(person));
    }

    /**
     * 修改person
     */
    @RequiresPermissions("assert:person:edit")
    @Log(title = "person", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Person person) {
        return toAjax(personService.updatePerson(person));
    }

    /**
     * 删除person
     */
    @RequiresPermissions("assert:person:remove")
    @Log(title = "person", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(personService.deletePersonByIds(ids));
    }
}
