(function (ng) {

    var mod = ng.module("rutinaModule");
    mod.constant("rutinaContext", "api/usuarios/");
    mod.controller('rutinasCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            $http.get(EstadoContext + $state.params.UsuarioId + "/rutinas").then(function (response) {
                $scope.Rutinas = response.data;
                console.info($scope.Rutinas);
                $scope.UID = $state.params.UsuarioId;
            });

        }]);

    mod.controller('createRutinaCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            $scope.createRutina = function ()
            {
                 var cdate = new Date();
                 cdate.setHours(0,0,0,0);
                 var dd = $scope.fechaEstado;
                 if(cdate<=dd)
                 {
                      $http.post(EstadoContext + +$state.params.UsId + "/estados/", {
                    fecha: $scope.fechaEstado
                  
                    
                }).then(function () {
                    $state.go(history.back());
                });  
                 }
                 else
                 {
                     window.alert("Debe ingresar una fecha valida");
                 }
             
   
            };
         

        }
    ]);
}
)(angular);


