(function (ng) {
    var mod = ng.module("objetivoModule");
    mod.constant("objetivoContext", "api/objetivos");
    mod.controller('objetivoCtrl', ['$scope', '$http', 'objetivoContext', '$state',
        function ($scope, $http, objetivoContext, $state) {
            console.info("dar datos objetivos");
            $http.get(objetivoContext).then(function (response) {
                $scope.objetivosRecords = response.data;
            });

            if ($state.params.objetivosId !== undefined) {
                $http.get(objetivoContext + '/' + $state.params.objetivosId).then(function (response) {
                    $scope.atributosDeCalidadRecords = response.data.calidad;
                });
            }
        }
    ]);
}
)(angular);