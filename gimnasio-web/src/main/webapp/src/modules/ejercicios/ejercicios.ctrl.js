(function (ng) {
    var mod = ng.module("ejercicioModule");
    mod.constant("ejerciciosContext", "api/ejercicios");

    mod.controller('ejercicioDetailCtrl', ['$scope', '$http', 'ejerciciosContext', '$state',
        function ($scope, $http, ejerciciosContext, $state) {
            if ($state.params.ejercicioId !== undefined && $state.params.ejercicioId !== null) {
                $http.get(ejerciciosContext + '/' + $state.params.ejercicioId).then(function (response) {
                    $scope.currentEjercicio = response.data;
                    $scope.currentvideo = "http://www.youtube.com/embed/" + response.data.video;
                    $scope.ejercicioInstanciaRecords = response.data.instancias;
                });
            }
        }
    ]).config(function ($sceDelegateProvider) {
        $sceDelegateProvider.resourceUrlWhitelist(['**']);
    });

    mod.controller('ejercicioCtrl', ['$scope', '$http', 'ejerciciosContext',
        function ($scope, $http, ejerciciosContext) {
            $http.get(ejerciciosContext).then(function (response) {
                $scope.ejerciciosRecords = response.data.sort(function (a, b) {
                    return a.descricpion.localeCompare(b.descricpion);
                });
            });

            $scope.buscarEjercicio = function () {
                var a = $scope.Nombre.toLowerCase();
                if (a !== undefined && a !== null) {
                    $http.get(ejerciciosContext).then(function (response) {
                        $scope.ejerciciosRecords = response.data.filter(function (c) {
                            return c.descricpion.toLowerCase().includes(a);
                        }).sort(function (a, b) {
                            return a.descricpion.localeCompare(b.descricpion);
                        });
                    });
                }
            };
        }
    ]);

    mod.controller('ejercicioDeleteCtrl', ['$scope', '$http', 'ejerciciosContext', '$state',
        function ($scope, $http, ejerciciosContext, $state) {
            var idEjercicio = $state.params.ejercicioId;
            $scope.deleteEjercicio = function () {
                $http.delete(ejerciciosContext + '/' + idEjercicio, {}).then(function (response) {
                    $state.go('ejercicios_list', {ejercicioId: response.data.id}, {reload$s: true});
                });
            };
        }
    ]);

    mod.controller('ejercicioNewCtrl', ['$scope', '$http', 'ejerciciosContext', '$state', '$rootScope',
        function ($scope, $http, ejerciciosContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.createEjercicio = function () {
                var videos = $scope.ejercicioVideo.split("=");
                $http.post(ejerciciosContext, {
                    descricpion: $scope.ejercicioDescricpion,
                    explicacion: $scope.ejercicioExplicacion,
                    tipo: $scope.ejercicioTipo,
                    video: videos[videos.length - 1],
                    pasos: $scope.ejercicioPasos.split("\n")
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
                var listPasos = "";
                if (ejercicio.pasos !== undefined) {
                    for (var i = 0; i < ejercicio.pasos.length - 1; i++) {
                        listPasos = listPasos + ejercicio.pasos[i] + "\n";
                    }
                    listPasos = listPasos + ejercicio.pasos[ejercicio.pasos.length - 1];
                }
                $scope.ejercicioTipo = ejercicio.tipo;
                $scope.ejercicioDescricpion = ejercicio.descricpion;
                $scope.ejercicioExplicacion = ejercicio.explicacion;
                $scope.ejercicioVideo = ejercicio.video;
                $scope.ejercicioPasos = listPasos;
            });

            $scope.createEjercicio = function () {
                var videos = $scope.ejercicioVideo.split("=");
                $http.put(ejerciciosContext + "/" + idEjercicio, {
                    descricpion: $scope.ejercicioDescricpion,
                    explicacion: $scope.ejercicioExplicacion,
                    tipo: $scope.ejercicioTipo,
                    video: videos[videos.length - 1],
                    pasos: $scope.ejercicioPasos.split("\n")
                }).then(function (response) {
                    $state.go('ejercicios_list', {ejercicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);