app.service("brandService",function ($http) {
    //查询所有数据
      this.findAll=function () {
          return $http.get("../brand/testPage.do");
      }

      //分页查询
      this.findPage=function () {
          return  $http.get("../brand/testAll.do?page=" + page + "&rows=" + rows);
      }

      //添加
      this.add=function (entity) {
          return $http.post("../brand/add.do",entity);
      }

      //修改
      this.update=function (entity) {
          return $http.post("../brand/update.do",entity);
      }


    //根据id查询
      this.findone=function () {
          return  $http.get("../brand/findone.do?id="+id);
      }

      //删除
      this.delete=function (arrayids) {
          return $http.get("../brand/delete.do?ids="+arrayids);
      }

    //搜索
     this.search =function (searchEntity,page,rows) {
         return $http.post("../brand/search.do?page="+page+"&rows="+rows,searchEntity);
     }
})