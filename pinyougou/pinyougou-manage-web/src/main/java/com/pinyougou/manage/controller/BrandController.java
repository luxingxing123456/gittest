package com.pinyougou.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Result;
import org.springframework.web.bind.annotation.*;



import java.util.List;
@RequestMapping("/brand")
@RestController
public class BrandController {

 @Reference
 public BrandService brandService;

@GetMapping("/findAll")
 public List<TbBrand> findAll(){
   return brandService.queryAll();
 }

 @GetMapping("/testPage")
 public List<TbBrand> testpage( @RequestParam(value = "page" ,defaultValue = "1") Integer page, @RequestParam (value = "rows",defaultValue = "10")Integer rows){


    //return brandService.testpage(page,rows);
 return (List<TbBrand>) brandService.findPage(page, rows).getRows();
 }


 @GetMapping("/testAll")
 public PageResult testall(@RequestParam(value = "page" ,defaultValue = "1") Integer page, @RequestParam (value = "rows",defaultValue = "10")Integer rows){


  //return brandService.testpage(page,rows);
  return (PageResult) brandService.findPage(page, rows);
 }

 //新增
 @PostMapping("/add")
 public Result add(@RequestBody TbBrand brand){
  try {
   brandService.add(brand);
   return Result.ok("数据添加成功");
  } catch (Exception e) {
   e.printStackTrace();
  }

  return Result.fail("数据添加失败");
 }

 //更新数据
 @GetMapping("/findone")
 public TbBrand  findone(Long id){
  return brandService.findOne(id);
 }
 @PostMapping("/update")
 public Result update(@RequestBody TbBrand brand){
  try {
   brandService.update(brand);
   return Result.ok("数据添加成功");
  } catch (Exception e) {
   e.printStackTrace();
  }

  return Result.fail("数据添加失败");
 }

 //批量删除
@GetMapping("/delete")
 public Result delete(Long[] ids){
  try {
   brandService.deleteByIds(ids);
   return Result.ok("数据删除成功");
  } catch (Exception e) {
   e.printStackTrace();
  }

  return Result.fail("数据删除失败");
 }

 //搜索产品
 @PostMapping("/search")
 public PageResult search(@RequestBody(required = false) TbBrand brand,@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "rows",defaultValue = "10") Integer rows){

  return brandService.search(brand,page,rows);

 }
}
