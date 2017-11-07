(function (ng) {
    var mod = ng.module("entrenadorModule");
    mod.constant("entrenadorContext", "api/entrenadores");
    mod.controller('entrenadoresCtrl', ['$scope', '$http', 'entrenadorContext', '$state',
       function ($scope, $http, entrenadorContext, $state) {
            //console.info("dar datos entrenador");
            $http.get(entrenadorContext).then(function (response) {
                $scope.entrenadorRecords = response.data;
                
                if (($state.params.Eid !== undefined) && ($state.params.Eid !== null))
                {
                    $http.get(entrenadorContext + '/' + $state.params.Eid).then(function(response)
                    {
                       var entrenadorActua = response.data;
                        entrenadorActua.usuarios.forEach(function(element){
                            if(element.genero === 'true')
                            {
                                element.genero = 'M';
                            }else
                            {
                                element.genero = 'F';
                            }
                        });
                        $scope.entrenadorActual = entrenadorActua ;
                        //console.info(entrenadorActua);
                    });
                }
                
                if (($state.params.Enid !== undefined) && ($state.params.Enid !== null))
                {
                     $http.get(entrenadorContext + '/' + $state.params.Enid + '/usuarios/' + $state.params.Uid).then(function(response)
                    {
                        //console.info(response.data);
                        $scope.usuarioActual = response.data;
                        
                    });
                }
                
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
            };
            
            this.deleteUsuarioEntrenador= function(usuario,entrenadorid){
                //console.info(entrenadorContext + "/" + entrenadorid + "/usuarios/"+ usuario.id);
                return $http.delete(entrenadorContext + "/" + entrenadorid + "/usuarios/"+ usuario.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.entrenadorActual.usuarios.indexOf(usuario);                               
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
            };
            
            $scope.createEstudiante = function()
            {
                console.info(entrenadorContext +"/"+ $state.params.Xid + "/usuarios/" + $scope.idUsuarioC);
                $http.post(
                        entrenadorContext +"/"+ $state.params.Xid + "/usuarios/" + $scope.idUsuarioC,{}
                        ).then(function (response) {
                    //se crea exitosamente el entrenador
                    $state.go('entrenadoresList', {id: response.data.id}, {reload: true});
                });
            };
        }
        
        
    ]);
}
)(angular);


