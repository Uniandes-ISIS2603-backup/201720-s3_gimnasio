(function (ng) {
    var mod = ng.module("objetivoModule");
    mod.constant("objetivosContext", "api/objetivos");
    mod.controller('objetivoCtrl', ['$scope', '$http', 'objetivosContext', '$state',
        function ($scope, $http, objetivosContext, $state) {
            $http.get(objetivosContext).then(function (response) {
                $scope.objetivosRecords = response.data;
            });

            if ($state.params.objetivoId !== undefined && $state.params.objetivoId !== null) {
                $http.get(objetivosContext + '/' + $state.params.objetivoId).then(function (response) {
                    $scope.currentObjetivo = response.data;
                    $scope.atributosDeCalidadRecords = response.data.calidad;
                    $scope.ejerciciosRecords = response.data.ejercicios;
                    $scope.usuariosRecosrds = response.data.usuarios;
                });
            }
        }
    ]);

    mod.controller('objetivoNewCtrl', ['$scope', '$http', 'objetivosContext', '$state', '$rootScope',
        function ($scope, $http, objetivosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createObjetivo = function () {
                $http.post(objetivosContext, {
                    tipo: $scope.objetivoTipo.toUpperCase(),
                    descripcion: $scope.objetivoDescripcion
                }).then(function (response) {
                    $state.go('objetivosList', {objetivoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('objetivoDeleteCtrl', ['$scope', '$http', 'objetivosContext', '$state',
        function ($scope, $http, objetivosContext, $state) {
            console.info("a eliminar");
            var idObjetivo = $state.params.objetivoId;
            $scope.deleteObjetivo = function () {
                $http.delete(objetivosContext + '/' + idObjetivo, {}).then(function (response) {
                    $state.go('objetivosList', {objetivoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('objetivoUpdateCtrl', ['$scope', '$http', 'objetivosContext', '$state', '$rootScope',
        function ($scope, $http, objetivosContext, $state, $rootScope) {
            $rootScope.edit = true;
            var idObjetivo = $state.params.objetivoId;

            $http.get(objetivosContext + '/' + idObjetivo).then(function (response) {
                var objetivo = response.data;
                $scope.objetivoTipo = objetivo.tipo;
                $scope.objetivoDescripcion = objetivo.descripcion;
            });

            $scope.createObjetivo = function () {
                $http.put(objetivosContext + "/" + idObjetivo, {
                    tipo: $scope.objetivoTipo.toUpperCase(),
                    descripcion: $scope.objetivoDescripcion
                }).then(function (response) {
                    $state.go('objetivosList', {objetivoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);