(function (ng) {
    var mod = ng.module("medidaModule");
    mod.constant("MedidaContext", "api/usuarios/1/estados/");
  
    mod.controller('medidaCtrl', ['$scope', '$http', 'MedidaContext', '$state',
        function ($scope, $http, MedidaContext, $state) {
            console.info("dar datos medida");
            $http.get(MedidaContext + $state.params.estadoID +"/medidas").then(function (response) {
                $scope.medidaRecords = response.data;
                
                
            },
             $http.get("api/tipoMedidas/").then(function (response) {
                $scope.tipoRecords = response.data;
                       }
               )         
           )
          
        
           $scope.creatMedida = function()
            {
               
                $http.post(MedidaContext + $state.params.estadoID +"/medidas/" + $scope.tip, {
                    medida: $scope.medida           
                }).then(function (response) {
                    //Author created successfully
                   $state.go('medidaList', {id: response.data.id}, {reload: true});
                });
            }

        }
        
        
    ]);
    
}
)(angular);
