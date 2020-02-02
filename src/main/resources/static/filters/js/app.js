angular.module("App",[]);
angular.module("App").controller("MainController",function($scope){
    $scope.title = "Lista de produtos";
    $scope.livros = [
        {id: 1,titulo:"Livro1",descricao:"Descrição Livro1",valor:45.56},
        {id: 2,titulo:"Livro2",descricao:"Descrição Livro2",valor:65.56},
        {id: 3,titulo:"Livro3",descricao:"Descrição Livro3",valor:35.56},
        {id: 4,titulo:"Livro4",descricao:"Descrição Livro4",valor:85.56}
    ];
    $scope.col = 'id';
    $scope.order = false;
    $scope.ordenaColunas = function(col){
        $scope.col = col;
        $scope.order = !$scope.order;
    };

});