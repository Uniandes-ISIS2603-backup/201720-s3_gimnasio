(function (ng) {
    var mod = ng.module("entrenadorModule");
    mod.constant("entrenadorContext", "api/entrenadores");
    mod.controller('entrenadoresCtrl', ['$scope', '$http', 'entrenadorContext', '$state',
       function ($scope, $http, entrenadorContext, $state) {
            console.info("dar datos entrenador");
            $http.get(entrenadorContext).then(function (response) {
                $scope.entrenadorRecords = response.data;
                console.info(response.data);
            });
            
            //borrar
            this.deleteRecord = function(entrenador){
                    return $http.delete(entrenadorContext + "/" + entrenador.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.entrenadorRecords.indexOf(entrenador);                               
                                if (index > -1) {
                                    $scope.entrenadorRecords.splice(index, 1);
                                }
                            });
            }
            
            $scope.createEntrenador = function()
            {
                $http.post(entrenadorContext, {
                    name: $scope.nombreEntrenador,
                    fechaNacimiento: $scope.fechaEntrenador,
                    documento: $scope.documentoEntrenador

                }).then(function (response) {
                    //se crea exitosamente el entrenador
                    $state.go('entrenadoresList', {id: response.data.id}, {reload: true});
                });
            }
        }
        
        
    ]);
}
)(angular);


