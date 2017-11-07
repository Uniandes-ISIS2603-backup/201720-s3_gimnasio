(function (ng) {
    var mod = ng.module("medidaModule");
    mod.constant("medidasContext", "api/objetivos");
    mod.constant("tipoMedidaContext", "api/tipoMedidas");
    mod.controller('medidaCtrl', ['$scope', '$http', 'medidasContext', '$state',
        function ($scope, $http, medidasContext, $state) {
            var medidaContext=medidasContext+ '/' + $state.params.objetivoId+ '/'+"atributosDeMedida";
            
            $http.get(medidaContext).then(function (response) {
                $scope.medidasRecords = response.data;
            });

            if ($state.params.medidaId !== undefined && $state.params.medidaId !== null) {
                $http.get(medidasContext + '/' + $state.params.medidaId).then(function (response) {
                    $scope.currentMedida = response.data;
                });
            }
        }
    ]);

    mod.controller('medidaDeleteCtrl', ['$scope', '$http', 'medidasContext', '$state',
        function ($scope, $http, medidasContext, $state) {
            var medidaContext=medidasContext+ '/' + $state.params.objetivoId+ '/'+"atributosDeMedida";
            var idMedida = $state.params.medidaId;
            $scope.deleteMedida = function () {
                $http.delete(medidaContext + '/' + idMedida, {}).then(function (response) {
                    $state.go('medidasList', {medidaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('medidaNewCtrl', ['$scope', '$http', 'medidasContext','tipoMedidaContext', '$state', '$rootScope',
        function ($scope, $http, medidasContext,tipoMedidaContext, $state, $rootScope) {
            var medidaContext=medidasContext+ '/' + $state.params.objetivoId+ '/'+"atributosDeMedida";
            
            $http.get(tipoMedidaContext).then(function (response) {
                $scope.medidasRecords = response.data;
            });
            
            $rootScope.edit = false;
            $scope.createMedida = function () {
                $http.post(medidaContext + '/' + $scope.medidaMedida, {
                    regresion: $scope.medidaRegresion
                }).then(function (response) {
                    $state.go('medidasList', {medidaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);