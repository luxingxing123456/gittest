package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.service.impl.BaseServiceImpl;
import com.pinyougou.vo.PageResult;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;


import java.util.List;
@Service(interfaceClass = BrandService.class)

public class BrandServiceImpl extends BaseServiceImpl<TbBrand> implements BrandService {

 @Autowired
 public BrandMapper brandMapper;
 @Override
 public List<TbBrand> queryAll() {
  return brandMapper.queryAll();
 }

 @Override
 public List<TbBrand> testpage(Integer page, Integer rows) {
     //使用分页助手和通用mapper实现,调用一次分页助手，只对一个查询有用
  PageHelper.startPage(page,rows);
  return brandMapper.selectAll();

 }

 @Override
 public PageResult search(TbBrand brand, Integer page, Integer rows) {
        //设置分页
       PageHelper.startPage(page,rows);
        //设置查询条件
  Example example = new Example(TbBrand.class);
  Example.Criteria criteria = example.createCriteria();

         //传入条件
      if(!StringUtils.isEmpty(brand.getFirstChar())){

       criteria.andEqualTo("firstChar", brand.getFirstChar());
      }

      if(!StringUtils.isEmpty(brand.getName())){
        criteria.andLike("name","%"+brand.getName()+"%");
      }

         //查询

  List<TbBrand> tbBrands = brandMapper.selectByExample(example);
  PageInfo<TbBrand>  pageInfo = new PageInfo<>(tbBrands);
  return new PageResult(pageInfo.getTotal(),pageInfo.getList());
 }
}
