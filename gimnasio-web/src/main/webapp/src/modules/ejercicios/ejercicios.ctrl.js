(function (ng) {
    var mod = ng.module("ejercicioModule");
    mod.constant("ejerciciosContext", "api/ejercicios");
    mod.controller('ejercicioCtrl', ['$scope', '$http', 'ejerciciosContext', '$state',
        function ($scope, $http, ejerciciosContext, $state) {
            $http.get(ejerciciosContext).then(function (response) {
                $scope.ejerciciosRecords = response.data;
            });

            if ($state.params.ejercicioId !== undefined && $state.params.ejercicioId !== null) {
                $http.get(ejerciciosContext + '/' + $state.params.ejercicioId).then(function (response) {
                    $scope.currentEjercicio = response.data;
                    $scope.ejercicioInstanciaRecords = response.data.instancias;
                });
            }
        }
    ]);

    mod.controller('ejercicioDeleteCtrl', ['$scope', '$http', 'ejerciciosContext', '$state',
        function ($scope, $http, ejerciciosContext, $state) {
            var idEjercicio = $state.params.ejercicioId;
            $scope.deleteEjercicio = function () {
                $http.delete(ejerciciosContext + '/' + idEjercicio, {}).then(function (response) {
                    $state.go('ejerciciosList', {ejercicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('ejercicioNewCtrl', ['$scope', '$http', 'ejerciciosContext', '$state', '$rootScope',
        function ($scope, $http, ejerciciosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createEjercicio = function () {
                $http.post(ejerciciosContext, {
                    descricpion: $scope.ejercicioDescricpion.toUpperCase(),
                    explicacion: $scope.ejercicioExplicacion,
                    tipo: $scope.ejercicioTipo
                }).then(function (response) {
                    $state.go('ejerciciosList', {ejercicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('ejercicioUpdateCtrl', ['$scope', '$http', 'ejerciciosContext', '$state', '$rootScope',
        function ($scope, $http, ejerciciosContext, $state, $rootScope) {
            $rootScope.edit = true;
            var idEjercicio = $state.params.ejercicioId;

            $http.get(ejerciciosContext + '/' + idEjercicio).then(function (response) {
                var ejercicio = response.data;
                $scope.ejercicioTipo = ejercicio.tipo;
                $scope.ejercicioDescricpion = ejercicio.descricpion;
                 $scope.ejercicioExplicacion = ejercicio.explicacion;
            });

            $scope.createEjercicio = function () {
                $http.put(ejerciciosContext + "/" + idEjercicio, {
                    descricpion: $scope.ejercicioDescricpion.toUpperCase(),
                    explicacion: $scope.ejercicioExplicacion,
                    tipo: $scope.ejercicioTipo
                }).then(function (response) {
                    $state.go('ejerciciosList', {ejercicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);