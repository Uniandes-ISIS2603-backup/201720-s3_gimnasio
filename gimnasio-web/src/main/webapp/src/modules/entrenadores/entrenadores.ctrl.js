(function (ng) {
    var mod = ng.module("entrenadorModule");
    mod.constant("entrenadorContext", "api/entrenadores");
    mod.controller('entrenadoresCtrl', ['$scope', '$http', 'entrenadorContext', '$state',
        function ($scope, $http, entrenadorContext, $state) {
            console.info("dar datos entrenador");
            $http.get(entrenadorContext).then(function (response) {
                $scope.entrenadorRecords = response.data;
                console.info(response.data);
            });
            
        }
    ]);
}
)(angular);


