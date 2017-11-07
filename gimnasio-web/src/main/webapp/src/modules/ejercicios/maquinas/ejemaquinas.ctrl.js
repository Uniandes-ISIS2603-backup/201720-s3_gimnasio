(function (ng) {
    var mod = ng.module("ejemaquinaModule");
    mod.constant("ejemaquinasContext", "api/ejercicios");
    mod.constant("tipoEjemaquinaContext", "api/maquinas");
    mod.controller('ejemaquinaCtrl', ['$scope', '$http', 'ejemaquinasContext', '$state',
        function ($scope, $http, ejemaquinasContext, $state) {
            var ejemaquinaContext=ejemaquinasContext+ '/' + $state.params.ejercicioId+ '/'+"maquinas";
            
            $http.get(ejemaquinaContext).then(function (response) {
                $scope.ejemaquinasRecords = response.data;
            });
        }
    ]);

    mod.controller('ejemaquinaDeleteCtrl', ['$scope', '$http', 'ejemaquinasContext', '$state',
        function ($scope, $http, ejemaquinasContext, $state) {
            var ejemaquinaContext=ejemaquinasContext+ '/' + $state.params.ejercicioId+ '/'+"maquinas";
            var idEjemaquina = $state.params.ejemaquinaId;
            $scope.deleteEjemaquina = function () {
                $http.delete(ejemaquinaContext + '/' + idEjemaquina, {}).then(function (response) {
                    $state.go('ejemaquinasList', {ejemaquinaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('ejemaquinaNewCtrl', ['$scope', '$http', 'ejemaquinasContext','tipoEjemaquinaContext', '$state', '$rootScope',
        function ($scope, $http, ejemaquinasContext,tipoEjemaquinaContext, $state, $rootScope) {
            var ejemaquinaContext=ejemaquinasContext+ '/' + $state.params.ejercicioId+ '/'+"maquinas";
            
            $http.get(tipoEjemaquinaContext).then(function (response) {
                $scope.ejemaquinasRecords = response.data;
            });
            
            $scope.createEjemaquina = function () {
                $http.post(ejemaquinaContext + '/' + $scope.ejemaquinaId).then(function (response) {
                    $state.go('ejemaquinasList', {ejemaquinaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);