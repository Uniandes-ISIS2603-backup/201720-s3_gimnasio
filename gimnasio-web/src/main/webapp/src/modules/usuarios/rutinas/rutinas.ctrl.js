(function (ng) {

    var mod = ng.module("rutinaModule");
    mod.constant("rutinaContext", "api/usuarios/");
    mod.controller('rutinasCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            console.info("entre");

        }]);

    mod.controller('createRutinaCtrl', ['$scope', '$http', 'EstadoContext', '$state',
        function ($scope, $http, EstadoContext, $state) {
            $scope.createRutina = function ()
            {
                 var cdate = new Date();
                 cdate.setHours(0,0,0,0);
                 var dd = $scope.fechaInicio;
                 
                 var cdate2 = new Date();
                 cdate2.setHours(0,0,0,0);
                 var dd2 = $scope.fechaInicio;
                 if(cdate<=dd && cdate2<=dd2)
                 {
                     var fechaI = cdate.getDate() + '/' + (cdate.getMonth() + 1) + '/' + cdate.getFullYear();
                     var fechaF = cdate2.getDate() + '/' + (cdate2.getMonth() + 1) + '/' + cdate2.getFullYear();
                      $http.post(EstadoContext +$state.params.UsId + "/rutinas", {
                    fechaInicio: fechaI,
                    
                    fechaFinal: fechaF
                  
                    
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


