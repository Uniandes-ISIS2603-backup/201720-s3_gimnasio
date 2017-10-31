(function (ng) {
    var mod = ng.module("EstadoModule");
    mod.constant("EstadoContext", "api/usuarios/1/estados/");
    mod.controller('estadoCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            console.info("dar datos estados");
            $http.get(EstadoContext).then(function (response) {
                $scope.estadosRecords = response.data;
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

        }
    ]);
}
)(angular);
