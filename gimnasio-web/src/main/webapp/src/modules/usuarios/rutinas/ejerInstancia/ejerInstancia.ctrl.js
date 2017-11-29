(function (ng) {
    var mod = ng.module("instanciaModule");
    mod.constant("instanciasContext", "api/usuarios");
    mod.constant("ejercicioContext", "api/ejercicios");
    mod.controller('instanciaCtrl', ['$scope', '$http', 'instanciasContext', '$state',
        function ($scope, $http, instanciasContext, $state) {
            $scope.idUsuario = $state.params.usuariosId;
            $scope.idRutina = $state.params.rutinaId;
            var instanciaContext = instanciasContext + '/' + $state.params.usuariosId + "/rutinas/" + $state.params.rutinaId + "/ejercicios";

            $http.get(instanciaContext).then(function (response) {
                $scope.instanciasRecords = response.data;
            });

            if ($state.params.instanciaId !== undefined && $state.params.instanciaId !== null) {
                $http.get(instanciaContext + '/' + $state.params.instanciaId).then(function (response) {
                    $scope.currentInstancia = response.data;

                    $http.get(instanciaContext + '/' + $state.params.instanciaId + "/graph").then(function (response) {
                        var a = response.data;
                        $(function () {
                            $('#container').highcharts({
                                chart: {
                                    type: 'areaspline',
                                    zoomType: 'x'
                                },
                                title: {
                                    text: 'GRAFICA CUMPLIMIENTO'
                                },
                                legend: {
                                    enabled: false
                                },
                                xAxis: {
                                    categories: a.ejerx
                                },
                                yAxis: {
                                    title: {
                                        text: 'series'
                                    }
                                },
                                series: [{
                                        data: a.valores
                                    }]
                            });
                        });
                    });
                });


            }

        }
    ]);

    mod.controller('instanciaDeleteCtrl', ['$scope', '$http', 'instanciasContext', '$state',
        function ($scope, $http, instanciasContext, $state) {
            var instanciaContext = instanciasContext + '/' + $state.params.usuariosId + "/rutinas/" + $state.params.rutinaId + "/ejercicios";
            var idInstancia = $state.params.instanciaId;
            $scope.deleteInstancia = function () {
                $http.delete(instanciaContext + '/' + idInstancia, {}).then(function (response) {
                    $state.go('instanciasList', {instanciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);


    mod.controller('instanciaNewCtrl', ['$scope', '$http', 'instanciasContext', 'ejercicioContext', '$state', '$rootScope',
        function ($scope, $http, instanciasContext, ejercicioContext, $state, $rootScope) {
            var instanciaContext = instanciasContext + '/' + $state.params.usuariosId + "/rutinas/" + $state.params.rutinaId + "/ejercicios";

            $http.get(ejercicioContext).then(function (response) {
                $scope.ejerciciosRecords = response.data;
            });

            $rootScope.edit = false;
            $scope.createInstancia = function () {
                $http.post(instanciaContext + '/' + $scope.instanciaEjercicio, {
                    series: $scope.instanciaSeries,
                    duracion: $scope.instanciaDuracion,
                    tamanioParticiones: $scope.instanciaTamanioParticiones,
                    repeticionesPorParticion: $scope.instanciaRepeticionesPorParticion
                }).then(function (response) {
                    $state.go('instanciasList', {instanciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('instanciaUpdateCtrl', ['$scope', '$http', 'instanciasContext', '$state', '$rootScope',
        function ($scope, $http, instanciasContext, $state) {
            var idInstancia = $state.params.instanciaId;
            var instanciaContext = instanciasContext + '/' + $state.params.usuariosId + "/rutinas/" + $state.params.rutinaId + "/ejercicios";

            $http.get(instanciaContext + "/" + idInstancia).then(function (response) {
                var instancia = response.data;
                $scope.instanciaSeries = instancia.series;
                $scope.instanciaDuracion = instancia.duracion;
                $scope.instanciaTamanioParticiones = instancia.tamanioParticiones;
                $scope.instanciaRepeticionesPorParticion = instancia.repeticionesPorParticion;
            });

            $scope.createInstancia = function () {
                $http.put(instanciaContext + "/" + idInstancia, {
                    series: $scope.instanciaSeries,
                    duracion: $scope.instanciaDuracion,
                    tamanioParticiones: $scope.instanciaTamanioParticiones,
                    repeticionesPorParticion: $scope.instanciaRepeticionesPorParticion
                }).then(function (response) {
                    $state.go('instanciasList', {instanciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    mod.controller('ejercicioHechoNewCtrl', ['$scope', '$http', 'UsuariosEjerciciosHechosContext', '$state', '$rootScope',

        function ($scope, $http, UsuariosEjerciciosHechosContext, $state, $rootScope)
        {
            var ejercicioHechoContext = UsuariosEjerciciosHechosContext + '/' + $state.params.usuariosId + "/rutinas/" +
                    $state.params.rutinaId + "/ejercicios/" + $state.params.instanciaId + "/ejerciciosHechos";

            $http.get(ejercicioHechoContext).then(function (response) {
                $scope.ejerciciosHechosRecords = response.data;
            });

            $rootScope.edit = false;
            $scope.createEjercicioHecho = function ()
            {
                $http.post(ejercicioHechoContext,
                        {
                            fecha: $scope.ejercicioHechoFecha,
                            series: $scope.ejercicioHechoSeries
                        }).then(function (response)
                {
                    $state.go('ejerciciosHechosList', {ejerciciosHechosId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);