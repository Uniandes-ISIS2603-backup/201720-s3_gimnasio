(function (ng) {
    var mod = ng.module("ejerciciosHechosModule");
    mod.constant("UsuariosEjerciciosHechosContext", "api/usuarios");
    mod.controller('ejerciciosHechosCtrl', ['$scope', '$http', 'UsuariosEjerciciosHechosContext', '$state',
        
        function ($scope, $http, UsuariosEjerciciosHechosContext, $state)         
        {
            var ejercicioHechoContext = UsuariosEjerciciosHechosContext+ '/' + $state.params.usuariosId+ "/rutinas/"+ 
                    $state.params.rutinaId+ "/ejercicios/" + $state.params.instanciaId + "/ejerciciosHechos";

            $http.get(ejercicioHechoContext).then(function (response) 
            {
                $scope.ejerciciosHechosRecords = response.data;
            });

            if (($state.params.ejerciciosHechosId !== undefined) && ($state.params.ejerciciosHechosId !== null)) {
                $http.get(ejercicioHechoContext + '/' + $state.params.ejerciciosHechosId).then(function (response) {
                    $scope.ejerciciosHechosRecords = response.data.records;
                    $scope.ejercicioHechoActual = response.data;
                });
            }
        }
    ]);  
    
}
)(window.angular);
