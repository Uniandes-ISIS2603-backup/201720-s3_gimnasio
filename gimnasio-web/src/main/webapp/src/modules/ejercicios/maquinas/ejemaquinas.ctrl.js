(function (ng) {
    var mod = ng.module("ejemaquinaModule");
    mod.constant("ejemaquinasContext", "api/ejercicios");
    mod.constant("tipoEjemaquinaContext", "api/maquinas");
    mod.controller('ejemaquinaCtrl', ['$scope', '$http', 'ejemaquinasContext', '$state',
        function ($scope, $http, ejemaquinasContext, $state) {
            var ejemaquinaContext = ejemaquinasContext + '/' + $state.params.ejercicioId + '/' + "maquinas";

            $http.get(ejemaquinaContext).then(function (response) {
                $scope.ejemaquinasRecords = response.data.sort(function (a, b) {
                    return a.descripcion.localeCompare(b.descripcion);
                });
            });
        }
    ]);

    mod.controller('ejemaquinaDeleteCtrl', ['$scope', '$http', 'ejemaquinasContext', '$state',
        function ($scope, $http, ejemaquinasContext, $state) {
            var ejemaquinaContext = ejemaquinasContext + '/' + $state.params.ejercicioId + '/' + "maquinas";
            var idEjemaquina = $state.params.ejemaquinaId;
            $scope.deleteEjemaquina = function () {
                $http.delete(ejemaquinaContext + '/' + idEjemaquina, {}).then(function (response) {
                    $state.go('ejemaquinasList', {ejemaquinaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('ejemaquinaNewCtrl', ['$scope', '$http', 'ejemaquinasContext', 'tipoEjemaquinaContext', '$state', '$rootScope',
        function ($scope, $http, ejemaquinasContext, tipoEjemaquinaContext, $state, $rootScope) {
            var ejemaquinaContext = ejemaquinasContext + '/' + $state.params.ejercicioId + '/' + "maquinas";

            $http.get(ejemaquinaContext).then(function (response) {
                var list = response.data;

                $http.get(tipoEjemaquinaContext).then(function (response) {
                    $scope.ejemaquinasRecords = response.data.filter(function (c) {
                        var encontrado = false;
                        for (var i = 0; i < list.length; i++) {
                            if (c.id === list[i].id)
                                encontrado = true;
                        }
                        return !encontrado;
                    }).sort(function (a, b) {
                        return a.descripcion.localeCompare(b.descripcion);
                    });
                });
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