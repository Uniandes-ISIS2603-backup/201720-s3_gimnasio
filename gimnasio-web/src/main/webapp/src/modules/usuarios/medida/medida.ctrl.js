(function (ng,estados) {
    var mod = ng.module("medidaModule");
    mod.constant("MedidaContext", "api/usuarios/1/estados/1/medidas");
    mod.controller('medidaCtrl', ['$scope', '$http', 'MedidaContext', '$state',
        function ($scope, $http, MedidaContext, $state) {
            console.info("dar datos medida");
            $http.get(MedidaContext).then(function (response) {
                $scope.medidaRecords = response.data;
            });
        }
    ]);
}
)(angular);
