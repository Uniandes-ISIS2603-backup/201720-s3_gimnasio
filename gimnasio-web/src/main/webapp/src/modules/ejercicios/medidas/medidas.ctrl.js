(function (ng) {
    var mod = ng.module("medidaModule");
    mod.constant("medidasContext", "api/ejercicios");
    mod.constant("tipoMedidaContext", "api/tipoMedidas");
    mod.controller('medidaCtrl', ['$scope', '$http', 'medidasContext', '$state',
        function ($scope, $http, medidasContext, $state) {
            var medidaContext=medidasContext+ '/' + $state.params.ejercicioId+ '/'+"tipoMedidas";
            
            $http.get(medidaContext).then(function (response) {
                $scope.medidasRecords = response.data;
            });
        }
    ]);

    mod.controller('medidaDeleteCtrl', ['$scope', '$http', 'medidasContext', '$state',
        function ($scope, $http, medidasContext, $state) {
            var medidaContext=medidasContext+ '/' + $state.params.ejercicioId+ '/'+"tipoMedidas";
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
            var medidaContext=medidasContext+ '/' + $state.params.ejercicioId+ '/'+"tipoMedidas";
            
            $http.get(tipoMedidaContext).then(function (response) {
                $scope.medidasRecords = response.data.filter(media => !media.automatico);
            });
            
            $scope.createMedida = function () {
                $http.post(medidaContext + '/' + $scope.medidaId).then(function (response) {
                    $state.go('medidasList', {medidaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);