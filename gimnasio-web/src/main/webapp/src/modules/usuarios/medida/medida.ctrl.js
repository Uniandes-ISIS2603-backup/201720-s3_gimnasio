(function (ng) {
    var mod = ng.module("medidaModule");
    mod.constant("MedidaContext", "api/usuarios/");
  
    mod.controller('medCtrl', ['$scope', '$http', 'MedidaContext', '$state',
        function ($scope, $http, MedidaContext, $state) {
            $http.get(MedidaContext +$state.params.UsuariosId+"/estados/"+ $state.params.estadoID +"/medidas").then(function (response) {
                $scope.medidaRecords = response.data;
                $scope.devusuar = $state.params.UsuariosId;
                 $scope.val = $state.params.valid;
            },
             $http.get("api/tipoMedidas/").then(function (response) {
                $scope.tipoRecords = response.data;
                       }
               )         
           ),
          
        
           $scope.creatMedida = function()
            {
               
                $http.post(MedidaContext +$state.params.UsuariosId+"/estados/"+ $state.params.estadoID +"/medidas/" + $scope.tipo.id, {
                    medida: $scope.medida           
                }).then(function (response) {
                    //Author created successfully
                   $state.go('medidaList', {id: response.data.id}, {reload: true});
                });
            };

        }
        
        
    ]);
    
}
)(angular);
