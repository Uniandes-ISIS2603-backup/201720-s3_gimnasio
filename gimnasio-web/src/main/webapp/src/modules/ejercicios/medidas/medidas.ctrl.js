(function (ng) {
    var mod = ng.module("ejemedidaModule");
    mod.constant("ejemedidasContext", "api/ejercicios");
    mod.constant("tipoEjemedidaContext", "api/tipoMedidas");
    mod.controller('ejemedidaCtrl', ['$scope', '$http', 'ejemedidasContext', '$state',
        function ($scope, $http, ejemedidasContext, $state) {
            var ejemedidaContext = ejemedidasContext + '/' + $state.params.ejercicioId + '/' + "tipoMedidas";

            $http.get(ejemedidaContext).then(function (response) {
                $scope.ejemedidasRecords = response.data.sort(function (a, b) {
                    return a.descripcion.localeCompare(b.descripcion);
                });
            });
        }
    ]);

    mod.controller('ejemedidaDeleteCtrl', ['$scope', '$http', 'ejemedidasContext', '$state',
        function ($scope, $http, ejemedidasContext, $state) {
            var ejemedidaContext = ejemedidasContext + '/' + $state.params.ejercicioId + '/' + "tipoMedidas";
            var idEjemedida = $state.params.ejemedidaId;
            $scope.deleteEjemedida = function () {
                $http.delete(ejemedidaContext + '/' + idEjemedida, {}).then(function (response) {
                    $state.go('ejemedidasList', {ejemedidaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('ejemedidaNewCtrl', ['$scope', '$http', 'ejemedidasContext', 'tipoEjemedidaContext', '$state', '$rootScope',
        function ($scope, $http, ejemedidasContext, tipoEjemedidaContext, $state, $rootScope) {
            var ejemedidaContext = ejemedidasContext + '/' + $state.params.ejercicioId + '/' + "tipoMedidas";

            $http.get(ejemedidaContext).then(function (response) {
                var list = response.data;

                $http.get(tipoEjemedidaContext).then(function (response) {
                    $scope.ejemedidasRecords = response.data.filter(function (c) {
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

            $scope.createEjemedida = function () {
                $http.post(ejemedidaContext + '/' + $scope.ejemedidaId).then(function (response) {
                    $state.go('ejemedidasList', {ejemedidaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);