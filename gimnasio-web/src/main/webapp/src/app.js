(function () {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies    
        
        'objetivoModule',
        'entrenadorModule',
        'EstadoModule',
        'medidaModule',
        'maquinaModule',
        'usuarioModule',
        'ejercicioModule',
        'ejerciciosHechosModule',
        'calidadModule',
        'tipoMedidaModule',
        'ejemedidaModule',
        'ejeobjetivoModule',
        'ejemaquinaModule',
        'ejerciciosHechosModule',
        'instanciaModule'
    ]);

    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);

})(window.angular);
