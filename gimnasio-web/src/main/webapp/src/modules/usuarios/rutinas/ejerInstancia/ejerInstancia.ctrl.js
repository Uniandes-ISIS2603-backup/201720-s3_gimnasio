(function (ng) {
    var mod = ng.module("instanciaModule");
    mod.constant("instanciasContext", "api/usuarios");
    mod.constant("ejercicioContext", "api/ejercicios");
    mod.controller('instanciaCtrl', ['$scope', '$http', 'instanciasContext', '$state',
        function ($scope, $http, instanciasContext, $state) {
            $scope.idUsuario=$state.params.usuariosId;
            $scope.idRutina=$state.params.rutinaId;
            var instanciaContext=instanciasContext+ '/' + $state.params.usuariosId+ "/rutinas/"+$state.params.rutinaId+"/ejercicios";
            
            $http.get(instanciaContext).then(function (response) {
                $scope.instanciasRecords = response.data;
            });

            if ($state.params.instanciaId !== undefined && $state.params.instanciaId !== null) {
                $http.get(instanciaContext + '/' + $state.params.instanciaId).then(function (response) {
                    $scope.currentInstancia = response.data;
                });
            }
        }
    ]);

    mod.controller('instanciaDeleteCtrl', ['$scope', '$http', 'instanciasContext', '$state',
        function ($scope, $http, instanciasContext, $state) {
            var instanciaContext=instanciasContext+ '/' + $state.params.usuariosId+ "/rutinas/"+$state.params.rutinaId+"/ejercicios";
            var idInstancia = $state.params.instanciaId;
            $scope.deleteInstancia = function () {
                $http.delete(instanciaContext + '/' + idInstancia, {}).then(function (response) {
                    $state.go('instanciasList', {instanciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('instanciaNewCtrl', ['$scope', '$http', 'instanciasContext','ejercicioContext', '$state', '$rootScope',
        function ($scope, $http, instanciasContext,ejercicioContext, $state, $rootScope) {
            var instanciaContext=instanciasContext+ '/' + $state.params.usuariosId+ "/rutinas/"+$state.params.rutinaId+"/ejercicios";
            
            $http.get(ejercicioContext).then(function (response) {
                $scope.ejerciciosRecords = response.data;
            });
            
            $rootScope.edit = false;
            $scope.createInstancia = function () {
                $http.post(instanciaContext + '/' + $scope.instanciaEjercicio, {
                    series: $scope.instanciaSeries,
                    duracion: $scope.instanciaDuracion,
                    tamanioParticiones:$scope.instanciaTamanioParticiones,
                    repeticionesPorParticion: $scope.instanciaRepeticionesPorParticion
                }).then(function (response) {
                    $state.go('instanciasList', {instanciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('instanciaUpdateCtrl', ['$scope', '$http', 'instanciasContext', '$state', '$rootScope',
        function ($scope, $http, instanciasContext, $state) {
            var idInstancia = $state.params.instanciaId;
            var instanciaContext=instanciasContext+ '/' + $state.params.usuariosId+ "/rutinas/"+$state.params.rutinaId+"/ejercicios";
            
            $http.get(instanciaContext + "/" + idInstancia).then(function (response) {
                var instancia = response.data;
                $scope.instanciaSeries= instancia.series;
                $scope.instanciaDuracion= instancia.duracion;
                $scope.instanciaTamanioParticiones= instancia.tamanioParticiones;
                $scope.instanciaRepeticionesPorParticion= instancia.repeticionesPorParticion;
            });

            $scope.createInstancia = function () {
                $http.put(instanciaContext + "/" + idInstancia, {
                    series: $scope.instanciaSeries,
                    duracion: $scope.instanciaDuracion,
                    tamanioParticiones:$scope.instanciaTamanioParticiones,
                    repeticionesPorParticion: $scope.instanciaRepeticionesPorParticion
                }).then(function (response) {
                    $state.go('instanciasList', {instanciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);