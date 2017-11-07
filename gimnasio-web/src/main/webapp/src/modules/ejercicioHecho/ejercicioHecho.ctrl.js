(function (ng) {
    var mod = ng.module("ejercicioHechoModule");
    mod.constant("ejercicioHechoContext", "api/ejerciciosHechos");
    mod.controller('ejercicioHechoCtrl', ['$scope', '$http', 'ejercicioHechoContext', '$state',
       function ($scope, $http, ejercicioHechoContext, $state) {
            //console.info("dar datos entrenador");
            $http.get(ejercicioHechoContext).then(function (response) {
                $scope.ejercicioHechoRecords = response.data;
                
                if (($state.params.Eid !== undefined) && ($state.params.Eid !== null))
                {
                    $http.get(ejercicioHechoContext + '/' + $state.params.Eid).then(function(response)
                    {
                       var ejercicioHechoActua = response.data;
                        ejercicioHechoActua.medicionMaquina.forEach(function(element){
                            element.medicion = 0;
                        });
                        $scope.ejercicioHechoActual = ejercicioHechoActua ;
                        //console.info(entrenadorActua);
                    });
                }
                
            });
            
            //borrar
            this.deleteRecord = function(ejercicioHecho){
                    return $http.delete(ejercicioHechoContext + "/" + ejercicioHecho.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.ejercicioHechoRecords.indexOf(ejercicioHecho);                               
                                if (index > -1) {
                                    $scope.ejercicioHechoRecords.splice(index, 1);
                                }
                            });
            };
            
            /*this.deleteUsuarioEntrenador= function(usuario,entrenadorid){
                //console.info(entrenadorContext + "/" + entrenadorid + "/usuarios/"+ usuario.id);
                return $http.delete(ejercicioHechoContext + "/" + ejercicioHechoid + "/medicionMaquina/"+ medicionMaquina.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.ejercicioHechoActual.usuarios.indexOf(usuario);                               
                                if (index > -1) {
                                    $scope.entrenadorActual.usuarios.splice(index, 1);
                                }
                            });
            };
            
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
            };*/
        }
        
        
    ]);
}
)(angular);
