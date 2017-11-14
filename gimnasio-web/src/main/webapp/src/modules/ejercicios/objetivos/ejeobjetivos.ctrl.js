(function (ng) {
    var mod = ng.module("ejeobjetivoModule");
    mod.constant("ejeobjetivosContext", "api/ejercicios");
    mod.constant("tipoEjeobjetivoContext", "api/objetivos");
    mod.controller('ejeobjetivoCtrl', ['$scope', '$http', 'ejeobjetivosContext', '$state',
        function ($scope, $http, ejeobjetivosContext, $state) {
            var ejeobjetivoContext = ejeobjetivosContext + '/' + $state.params.ejercicioId + '/' + "objetivos";

            $http.get(ejeobjetivoContext).then(function (response) {
                $scope.ejeobjetivosRecords = response.data.sort(function (a, b) {
                    return a.tipo.localeCompare(b.tipo);
                });
            });
        }
    ]);

    mod.controller('ejeobjetivoDeleteCtrl', ['$scope', '$http', 'ejeobjetivosContext', '$state',
        function ($scope, $http, ejeobjetivosContext, $state) {
            var ejeobjetivoContext = ejeobjetivosContext + '/' + $state.params.ejercicioId + '/' + "objetivos";
            var idEjeobjetivo = $state.params.ejeobjetivoId;
            $scope.deleteEjeobjetivo = function () {
                $http.delete(ejeobjetivoContext + '/' + idEjeobjetivo, {}).then(function (response) {
                    $state.go('ejeobjetivosList', {ejeobjetivoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('ejeobjetivoNewCtrl', ['$scope', '$http', 'ejeobjetivosContext', 'tipoEjeobjetivoContext', '$state', '$rootScope',
        function ($scope, $http, ejeobjetivosContext, tipoEjeobjetivoContext, $state, $rootScope) {
            var ejeobjetivoContext = ejeobjetivosContext + '/' + $state.params.ejercicioId + '/' + "objetivos";

            $http.get(ejeobjetivoContext).then(function (response) {
                var list = response.data;

                $http.get(tipoEjeobjetivoContext).then(function (response) {
                    $scope.ejeobjetivosRecords = response.data.filter(function (c) {
                        var encontrado = false;
                        for (var i = 0; i < list.length; i++) {
                            if (c.id === list[i].id)
                                encontrado = true;
                        }
                        return !encontrado;
                    }).sort(function (a, b) {
                        return a.tipo.localeCompare(b.tipo);
                    });
                });
            });

            $scope.createEjeobjetivo = function () {
                $http.post(ejeobjetivoContext + '/' + $scope.ejeobjetivoId).then(function (response) {
                    $state.go('ejeobjetivosList', {ejeobjetivoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);