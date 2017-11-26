(function (ng) {
    var mod = ng.module("medModule");
    mod.constant("MedidaContext", "api/usuarios/");
  
    mod.controller('medCtr', ['$scope', '$http', 'MedidaContext', '$state',
        function ($scope, $http, MedidaContext, $state) {
            console.info("dar datos medida");
                console.info($state.params.UsuariosId);
            $http.get(MedidaContext +$state.params.UsuariosId+"/estados/"+ $state.params.estadoID +"/medidas").then(function (response) {
                $scope.medidaRecords = response.data;
                $scope.devusuar = $state.params.UsuariosId;
                
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
