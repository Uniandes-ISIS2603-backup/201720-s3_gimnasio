(function (ng) {

    var mod = ng.module("EstadoModule");
    mod.constant("EstadoContext", "api/usuarios/");
    mod.controller('estadoCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            console.info("dar datos estados");
            console.info($state.params.UsuarioId);
            $http.get(EstadoContext + $state.params.UsuarioId + "/estados/").then(function (response) {
                $scope.estadosRecords = response.data;
             
                $scope.uid = $state.params.UsuarioId;
            });


            //borrado
            this.deleteRecord = function (estado) {
                return $http.delete(EstadoContext + "/" + estado.id)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                            var index = $scope.entrenadorRecords.indexOf(estado);
                            if (index > -1) {
                                $scope.entrenadorRecords.splice(index, 1);
                            }
                        });
            };
        }]);

    mod.controller('estadoCreateCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            $scope.createEstado = function ()
            {
                 var cdate = new Date();
                 var dd = $scope.fechaEstado;
                 if(cdate<dd)
                 {
                      $http.post(EstadoContext + +$state.params.UsId + "/estados/", {
                    fecha: $scope.fechaEstado
                  
                    
                }).then(function () {
                    $state.go(history.back());
                });  
                 }
                 else
                 {
                     window.alert("Debe ingresar una fecha valida");
                 }
             
   
            };
         

        }
    ]);
}
)(angular);
