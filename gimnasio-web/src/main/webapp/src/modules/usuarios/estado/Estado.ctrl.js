(function (ng) {
    var mod = ng.module("EstadoModule");
    mod.constant("EstadoContext", "api/usuarios/");
    mod.controller('estadoCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            console.info("dar datos estados");
            console.info($state.params.UsuarioId);
            $http.get(EstadoContext +$state.params.UsuarioId+"/estados/").then(function (response) {
                $scope.estadosRecords = response.data;
                $scope.uid = $state.params.UsuarioId;
            });
            
            
            //borrado
                        this.deleteRecord = function(estado){
                    return $http.delete(EstadoContext + "/" + estado.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.entrenadorRecords.indexOf(estado);                               
                                if (index > -1) {
                                    $scope.entrenadorRecords.splice(index, 1);
                                }
                            });
            }
             $scope.createEstado = function()
            {
                $http.post(EstadoContext + +$state.params.UsuarioId+"/estados/", {
                    fecha: $scope.fechaEstado           
                }).then(function (response) {
                    //Author created successfully
                   $state.go('estadoList', {id: response.data.id}, {reload: true});
                });
            }

        }
    ]);
}
)(angular);
