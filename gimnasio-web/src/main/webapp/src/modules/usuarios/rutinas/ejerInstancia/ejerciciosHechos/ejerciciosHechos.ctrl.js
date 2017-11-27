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
                $http.get(UsuariosEjerciciosHechosContext + '/' + $state.params.ejerciciosHechosId).then(function (response) {
                    $scope.ejercicioHechoActual = response.data;
                });
            }
        }
    ]);
    
    mod.controller('ejercicioHechoNewCtrl', ['$scope', '$http', 'UsuariosEjerciciosHechosContext', '$state', '$rootScope',
        
        function ($scope, $http, UsuariosEjerciciosHechosContext , $state, $rootScope) 
        {
            var ejercicioHechoContext = UsuariosEjerciciosHechosContext+ '/' + $state.params.usuariosId+ "/rutinas/"+ 
                    $state.params.rutinaId+ "/ejercicios/"  + $state.params.instanciaId + "/ejerciciosHechos";            
            
            $http.get(ejercicioHechoContext).then(function (response) {
                $scope.ejerciciosHechosRecords = response.data;
            });
            
            $rootScope.edit = false;
            $scope.createEjercicioHecho = function () 
            {
                $http.post(ejercicioHechoContext + '/' + $scope.ejercicioHecho, 
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
)(window.angular);
