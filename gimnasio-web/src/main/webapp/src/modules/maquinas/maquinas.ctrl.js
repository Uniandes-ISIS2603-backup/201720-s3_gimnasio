(function (ng) {
    var mod = ng.module("maquinaModule");
    mod.constant("maquinaContext", "api/maquinas");
    mod.controller('maquinasCtrl', ['$scope', '$http', 'maquinaContext', '$state',
        function ($scope, $http, maquinaContext, $state) {
            $http.get(maquinaContext).then(function (response) {
                $scope.maquinaRecords = response.data;
            });

            this.deleteRecord = function (maquina) {
                return $http.delete(maquinaContext + "/" + maquina.id)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                            var index = $scope.maquinaRecords.indexOf(maquina);
                            if (index > -1) {
                                $scope.maquinaRecords.splice(index, 1);
                            }
                        });
            };
            this.crearTipoMedida = function (maquina) {
                return $http.put(maquinaContext + "/" + maquina.id)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                        });
            };
            
             $scope.addTipoMedida = function (maquina, idTipoMedida)
            {
                console.info(maquinaContext + "/" + maquina.id + "/tipoMedidas/" + idTipoMedida);
                $http.post(
                        maquinaContext + "/" + maquina.id + "/tipoMedidas/" + idTipoMedida, {}
                ).then(function (response) {
                    //se agrego 
                    $state.go('maquinasList', {id: response.data.id}, {reload: true});
                });
            };
        }]);

    mod.controller('maquinasCreateCtrl', ['$scope', '$http', 'maquinaContext', '$state',
        function ($scope, $http, maquinaContext, $state) {
            $scope.createMaquina = function ()
            {
                $http.post(maquinaContext, {
                    descripcion: $scope.descripcionMaquina,
                    tipoMedida: $scope.tipoMedidaMaquina
                }).then(function (response) {
                    $state.go('maquinasList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);


