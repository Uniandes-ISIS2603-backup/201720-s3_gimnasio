(function (ng) {
    var mod = ng.module("EstadoModule");
    mod.constant("EstadoContext", "api/usuarios/1/estados/");
    mod.controller('estadoCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            console.info("dar datos estados");
            $http.get(EstadoContext).then(function (response) {
                $scope.estadosRecords = response.data;
            });

        }
    ]);
}
)(angular);
