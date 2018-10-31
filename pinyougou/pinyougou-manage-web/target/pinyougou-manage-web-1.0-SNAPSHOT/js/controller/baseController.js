app.controller("baseController",function ($scope) {
    //初始化分页
    $scope.paginationConf={
        currentPage:1,// 当前页号
        totalItems:10,// 总记录数
        itemsPerPage:10,// 页大小
        perPageOptions:[10, 20, 30, 40, 50],// 可选择的每页大小
        onChange: function () {// 当上述的参数发生变化了后触发
            $scope.reloadList();
        }
    };

    //加载页面信息
    $scope.reloadList = function () {
        // $scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    };

    //定义id数组
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
})