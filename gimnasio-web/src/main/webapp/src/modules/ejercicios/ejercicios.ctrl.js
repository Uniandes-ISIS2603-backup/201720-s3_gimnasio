(function (ng) {
    var mod = ng.module("ejercicioModule");
    mod.constant("ejerciciosContext", "api/ejercicios");
    mod.controller('ejercicioCtrl', ['$scope', '$http', 'ejerciciosContext', '$state',
        function ($scope, $http, ejerciciosContext, $state) {
            $http.get(ejerciciosContext).then(function (response) {
                $scope.ejerciciosRecords = response.data.sort(function (a, b) {
                    return a.descricpion.localeCompare(b.descricpion);
                });
            });

            if ($state.params.ejercicioId !== undefined && $state.params.ejercicioId !== null) {
                $http.get(ejerciciosContext + '/' + $state.params.ejercicioId).then(function (response) {
                    $scope.currentEjercicio = response.data;
                    $scope.currentEjercicio.video = "http://www.youtube.com/embed/" + response.data.video;
                    $scope.ejercicioInstanciaRecords = response.data.instancias;
                });
            }
        }
    ]).config(function($sceDelegateProvider) {
  $sceDelegateProvider.resourceUrlWhitelist(['**']);
});;

    mod.controller('ejercicioDeleteCtrl', ['$scope', '$http', 'ejerciciosContext', '$state',
        function ($scope, $http, ejerciciosContext, $state) {
            var idEjercicio = $state.params.ejercicioId;
            $scope.deleteEjercicio = function () {
                $http.delete(ejerciciosContext + '/' + idEjercicio, {}).then(function (response) {
                    $state.go('ejercicios_list', {ejercicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('ejercicioNewCtrl', ['$scope', '$http', 'ejerciciosContext', '$state', '$rootScope',
        function ($scope, $http, ejerciciosContext, $state, $rootScope) {
            $rootScope.edit = false;
            var videos=$scope.ejercicioVideo.split("=");
            $scope.createEjercicio = function () {
                $http.post(ejerciciosContext, {
                    descricpion: $scope.ejercicioDescricpion,
                    explicacion: $scope.ejercicioExplicacion,
                    tipo: $scope.ejercicioTipo,
                    video: videos[videos.length-1]
                }).then(function (response) {
                    $state.go('ejercicios_list', {ejercicioId: response.data.id}, {reload: true});
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
                $scope.ejercicioVideo= ejercicio.video;
            });

            $scope.createEjercicio = function () {
                var videos=$scope.ejercicioVideo.split("=");
                $http.put(ejerciciosContext + "/" + idEjercicio, {
                    descricpion: $scope.ejercicioDescricpion,
                    explicacion: $scope.ejercicioExplicacion,
                    tipo: $scope.ejercicioTipo,
                    video: videos[videos.length-1]
                }).then(function (response) {
                    $state.go('ejercicios_list', {ejercicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);