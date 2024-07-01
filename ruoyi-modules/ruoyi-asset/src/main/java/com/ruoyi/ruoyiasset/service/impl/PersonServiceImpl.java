package com.ruoyi.ruoyiasset.service.impl;

import com.ruoyi.ruoyiassert.service.IPersonService;
import com.ruoyi.ruoyiasset.domain.Person;
import com.ruoyi.ruoyiasset.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * personService业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-30
 */
@Service
public class PersonServiceImpl implements IPersonService 
{
    @Autowired
    private PersonMapper personMapper;

    /**
     * 查询person
     * 
     * @param id person主键
     * @return person
     */
    @Override
    public Person selectPersonById(Long id)
    {
        return personMapper.selectPersonById(id);
    }

    /**
     * 查询person列表
     * 
     * @param person person
     * @return person
     */
    @Override
    public List<Person> selectPersonList(Person person)
    {
        return personMapper.selectPersonList(person);
    }

    /**
     * 新增person
     * 
     * @param person person
     * @return 结果
     */
    @Override
    public int insertPerson(Person person)
    {
        return personMapper.insertPerson(person);
    }

    /**
     * 修改person
     * 
     * @param person person
     * @return 结果
     */
    @Override
    public int updatePerson(Person person)
    {
        return personMapper.updatePerson(person);
    }

    /**
     * 批量删除person
     * 
     * @param ids 需要删除的person主键
     * @return 结果
     */
    @Override
    public int deletePersonByIds(Long[] ids)
    {
        return personMapper.deletePersonByIds(ids);
    }

    /**
     * 删除person信息
     * 
     * @param id person主键
     * @return 结果
     */
    @Override
    public int deletePersonById(Long id)
    {
        return personMapper.deletePersonById(id);
    }
}
