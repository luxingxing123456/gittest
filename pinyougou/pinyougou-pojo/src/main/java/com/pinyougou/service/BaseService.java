package com.pinyougou.service;

import com.pinyougou.vo.PageResult;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
 /*
 * 考虑到后续对不同对象同样的操作比较多，所以将这些基础的方法抽取出来，
 *  因为是dubbl框架，所以需要提供服务就是接口，，service的接口依赖，pojo层，所以将接口写到pojo层
 *  实现类写dao层，因为service的实现，是依赖dao层的。
 *  可以继承实现其中的方法
 *
 * */
 //基本的增删改查
 //根据id查询
 public T findOne(Serializable id);

 //查询全部
 public List<T> findAll();

 //根据条件查询
 public List<T> findByWhere(T t);

 //分页查询，返回一个分页对象，所以要写一个实体类

 public PageResult findPage(Integer page, Integer rows);
 public PageResult findPage(Integer page, Integer rows, T t);

 //新增数据
 public void add(T t);

 //更新数据
 public void update(T t);

 //批量删除
 public void deleteByIds(Serializable[] ids);




}
