(function (ng) {
    var mod = ng.module("instanciaModule");
    mod.constant("instanciasContext", "api/usuarios");
    mod.constant("tipoMedidaContext", "api/tipoMedidas");
    mod.controller('instanciaCtrl', ['$scope', '$http', 'instanciasContext', '$state',
        function ($scope, $http, instanciasContext, $state) {
            var instanciaContext=instanciasContext+ '/' + $state.params.usuariosId+ "/rutinas/"+$state.params.rutinaId+"/ejercicios";
            
            $http.get(instanciaContext).then(function (response) {
                $scope.instanciasRecords = response.data;
            });

            if ($state.params.instanciaId !== undefined && $state.params.instanciaId !== null) {
                $http.get(instanciasContext + '/' + $state.params.instanciaId).then(function (response) {
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

    mod.controller('instanciaNewCtrl', ['$scope', '$http', 'instanciasContext','tipoMedidaContext', '$state', '$rootScope',
        function ($scope, $http, instanciasContext,tipoMedidaContext, $state, $rootScope) {
            var instanciaContext=instanciasContext+ '/' + $state.params.usuariosId+ "/rutinas/"+$state.params.rutinaId+"/ejercicios";
            
            $http.get(tipoMedidaContext).then(function (response) {
                $scope.medidasRecords = response.data;
            });
            
            $rootScope.edit = false;
            $scope.createInstancia = function () {
                $http.post(instanciaContext + '/' + $scope.instanciaMedida, {
                    regresion: $scope.instanciaRegresion
                }).then(function (response) {
                    $state.go('instanciasList', {instanciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('instanciaUpdateCtrl', ['$scope', '$http', 'instanciasContext','tipoMedidaContext', '$state', '$rootScope',
        function ($scope, $http, instanciasContext,tipoMedidaContext, $state, $rootScope) {
            $rootScope.edit = true;
            var idInstancia = $state.params.instanciaId;
            var instanciaContext=instanciasContext+ '/' + $state.params.objetivoId+ '/'+"atributosDeInstancia";
            
            $http.get(tipoMedidaContext).then(function (response) {
                $scope.medidasRecords = response.data;
            });

            $scope.createInstancia = function () {
                $http.put(instanciaContext + "/" + idInstancia, {
                    regresion: $scope.instanciaRegresion
                }).then(function (response) {
                    $state.go('instanciasList', {instanciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);