(function (ng) {
    var mod = ng.module("tipoMedidaModule");
    mod.constant("tipoMedidaContext", "api/tipoMedidas");
    mod.controller('tipoMedidasCtrl', ['$scope', '$http', 'tipoMedidaContext', '$state',
       function ($scope, $http, tipoMedidaContext, $state) {
            $http.get(tipoMedidaContext).then(function (response) {
                $scope.tipoMedidaRecords = response.data;
                
                if (($state.params.Eid !== undefined) && ($state.params.Eid !== null))
                {
                    $http.get(tipoMedidaContext + '/' + $state.params.Eid).then(function(response)
                    {
                       var tipoMedidaActua = response.data;
                        tipoMedidaActua.usuarios.forEach(function(element){
                            if(element.genero === 'true')
                            {
                                element.genero = 'M';
                            }else
                            {
                                element.genero = 'F';
                            }
                        });
                        $scope.tipoMedidaActual = tipoMedidaActua ;
                    });
                }
                
            });
            
            //borrar
            this.deleteRecord = function(tipoMedida){
                    return $http.delete(tipoMedidaContext + "/" + tipoMedida.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.tipoMedidaRecords.indexOf(tipoMedida);                               
                                if (index > -1) {
                                    $scope.tipoMedidaRecords.splice(index, 1);
                                }
                            });
            };
            
            this.deleteUsuariotipoMedida= function(usuario,tipoMedidaid){
                return $http.delete(tipoMedidaContext + "/" + tipoMedidaid + "/usuarios/"+ usuario.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.tipoMedidaActual.usuarios.indexOf(usuario);                               
                                if (index > -1) {
                                    $scope.tipoMedidaActual.usuarios.splice(index, 1);
                                }
                            });
            };
            
            $scope.createtipoMedida = function()
            {
                $http.post(tipoMedidaContext, {
                    descripcion: $scope.descripciontipoMedida,
                    unidad: $scope.unidadtipoMedida,

                }).then(function (response) {
                    //se crea exitosamente el tipoMedida
                    $state.go('tipoMedidasList', {id: response.data.id}, {reload: true});
                });
            };
        }
        
        
    ]);
}
)(angular);

