(function (ng) {
    var mod = ng.module("ejerciciosHechosModule");
    mod.constant("UsuariosEjerciciosHechosContext", "api/usuarios");
    mod.constant("EjercicioEjerciciosHechosContext", "api/ejercicios");  
    //mod.constant("InstanciaEjerciciosHechosContext", "aiuda");  
    mod.controller('ejerciciosHechosCtrl', ['$scope', '$http', 'ejerciciosHechosContext', '$state',
        
        function ($scope, $http, UsuariosEjerciciosHechosContext, EjercicioEjerciciosHechosContext , $state)         
        {
            var ejercicioHechoContext = UsuariosEjerciciosHechosContext+ '/' + $state.params.usuariosId+ "/rutinas/"+ 
                    $state.params.rutinaId+ "/" + EjercicioEjerciciosHechosContext + "/" + $state.params.instanciaId + "/ejerciciosHechos";

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
    
    mod.controller('ejercicioHechoNewCtrl', ['$scope', '$http', 'UsuariosEjerciciosHechosContext','EjercicioEjerciciosHechosContext' /*, 'InstanciaEjerciciosHechosContext' 
        ,*/,'$state', '$rootScope',
        
        function ($scope, $http, UsuariosEjerciciosHechosContext, EjercicioEjerciciosHechosContext/*, InstanciaEjerciciosHechosContext*/, $state, $rootScope) 
        {
            var ejercicioHechoContext = UsuariosEjerciciosHechosContext+ '/' + $state.params.usuariosId+ "/rutinas/"+ 
                    $state.params.rutinaId+ "/" + EjercicioEjerciciosHechosContext + "/" + $state.params.instanciaId + "/ejerciciosHechos";            
            
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

    mod.controller('ejercicioHechoUpdateCtrl', ['$scope', '$http', 'UsuariosEjerciciosHechosContext','EjercicioEjerciciosHechosContext' , 'InstanciaEjerciciosHechosContext' 
        ,'$state', '$rootScope',
        
        function ($scope, $http, UsuariosEjerciciosHechosContext, EjercicioEjerciciosHechosContext/*, InstanciaEjerciciosHechosContext*/, $state, $rootScope) 
        {
            var hechoId = $state.params.ejerciciosHechosId;
            var ejercicioHechoContext = UsuariosEjerciciosHechosContext+ '/' + $state.params.usuariosId+ "/rutinas/"+ 
                    $state.params.rutinaId+ "/" + EjercicioEjerciciosHechosContext + "/" + $state.params.instanciaId + "/ejerciciosHechos";              
            
            $http.get(ejercicioHechoContext + "/" + hechoId).then(function (response) 
            {
                var hecho = response.data;
                $scope.ejercicioHechoFecha= hecho.fecha;
                $scope.ejercicioHechoSeries= hecho.series;
            });

            $scope.createEjercicioHecho = function () {
                $http.put(ejercicioHechoContext + "/" + hechoId, 
                {
                    fecha: $scope.ejercicioHechoFecha,
                    series: $scope.ejercicioHechoSeries 
                    
                }).then(function (response) {
                    $state.go('ejerciciosHechosList', {ejerciciosHechosId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);
