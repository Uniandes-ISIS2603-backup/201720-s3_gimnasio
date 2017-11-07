(function (ng) {
    var mod = ng.module("calidadModule");
    mod.constant("calidadsContext", "api/objetivos");
    mod.controller('calidadCtrl', ['$scope', '$http', 'calidadsContext', '$state',
        function ($scope, $http, calidadsContext, $state) {
            var calidadContext=calidadsContext+ '/' + $state.params.objetivoId+ '/'+"atributosDeCalidad";
            
            $http.get(calidadContext).then(function (response) {
                $scope.calidadsRecords = response.data;
            });

            if ($state.params.calidadId !== undefined && $state.params.calidadId !== null) {
                $http.get(calidadsContext + '/' + $state.params.calidadId).then(function (response) {
                    $scope.currentCalidad = response.data;
                });
            }
        }
    ]);

    mod.controller('calidadDeleteCtrl', ['$scope', '$http', 'calidadsContext', '$state',
        function ($scope, $http, calidadsContext, $state) {
            var calidadContext=calidadsContext+ '/' + $state.params.objetivoId+ '/'+"atributosDeCalidad";
            var idCalidad = $state.params.calidadId;
            $scope.deleteCalidad = function () {
                console.info("borrando");
                $http.delete(calidadContext + '/' + idCalidad, {}).then(function (response) {
                    $state.go('calidadsList', {calidadId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('calidadNewCtrl', ['$scope', '$http', 'calidadsContext', '$state', '$rootScope',
        function ($scope, $http, calidadsContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createCalidad = function () {
                $http.post(calidadsContext, {
                    descricpion: $scope.calidadDescricpion.toUpperCase(),
                    explicacion: $scope.calidadExplicacion,
                    tipo: $scope.calidadTipo
                }).then(function (response) {
                    $state.go('calidadsList', {calidadId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('calidadUpdateCtrl', ['$scope', '$http', 'calidadsContext', '$state', '$rootScope',
        function ($scope, $http, calidadsContext, $state, $rootScope) {
            $rootScope.edit = true;
            var idCalidad = $state.params.calidadId;

            $http.get(calidadsContext + '/' + idCalidad).then(function (response) {
                var calidad = response.data;
                $scope.calidadTipo = calidad.tipo;
                $scope.calidadDescricpion = calidad.descricpion;
                 $scope.calidadExplicacion = calidad.explicacion;
            });

            $scope.createCalidad = function () {
                $http.put(calidadsContext + "/" + idCalidad, {
                    descricpion: $scope.calidadDescricpion.toUpperCase(),
                    explicacion: $scope.calidadExplicacion,
                    tipo: $scope.calidadTipo
                }).then(function (response) {
                    $state.go('calidadsList', {calidadId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);