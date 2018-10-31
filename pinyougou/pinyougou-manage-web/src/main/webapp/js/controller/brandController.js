app.controller("brandController",function ($scope,brandService) {

    //继承父类controller
    //$controller("baseController", {$scope:$scope});

    //查询所有列表的数据并绑定到brand对象
    $scope.findAll=function () {
        brandService.findAll().success(function (response) {
            $scope.brand=response;
        });
    }

    //初始化分页数据
    $scope.paginationConf={
        currentPage:1,// 当前页号
        totalItems:10,// 总记录数
        itemsPerPage:10,// 页大小
        perPageOptions:[10, 20, 30, 40, 50],// 可选择的每页大小
        onChange: function () {// 当上述的参数发生变化了后触发
            $scope.reloadList();
        }
    };
   //加载分页数据
    $scope.reloadList = function () {
        // $scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    };

   //分页查询
    $scope.findPage = function (page, rows) {
        brandService.findPage(page,rows).success(function (response) {
            $scope.brand = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    };

    //添加保存数据
    $scope.save=function () {
        var obj;
        if($scope.entity.id != null ){
            obj=brandService.update($scope.entity);
        }else{
            obj=brandService.add($scope.entity);
        };

        obj.success(function (response) {
            if(reponse.success){
                //重新加载列表
                $scope.reloadList;
            }else{
                alert(response.message);
            }
        }) };

    //根据id查询
    $scope.findone=function (id) {
        $http.get("../brand/findone.do?id="+id).success(function (response) {
            $scope.entity=response;
        })
    };

    //定义数组，添加删id组，用到JavaScript知识
    $scope.arrayids=[];
    $scope.deleteServlet=function ($event,id ) {

        if($event.target.checked){
            $scope.arrayids.push(id);
        }else{
            //定位
            var index = $scope.arrayids.indexOf(id);
            //删除
            $scope.arrayids.splice(1,index);
        }
    };

    //批量删除
    $scope.delete=function(){
        if($scope.arrayids.length<1){
            alert("请选择要删除的数据")
        }
        if(confirm("确定删除吗？")){
            brandService.delete($scope.arrayids).success(function (response) {
                if(response.success){
                    $scope.reloadList;
                    $scope.arrayids=[];
                }else{
                    alert(response.message);
                }
            })
        };
    };

    //根据条件查询
    $scope.searchEntity={};

    $scope.search=function (page,rows) {
       brandService.search($scope.searchEntity,page,rows).success(function (response) {
            $scope.brand=response.rows;
            $scope.paginationConf.totalItems=response.total;

        })
    };
})