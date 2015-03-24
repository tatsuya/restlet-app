'use strict';

angular.module('restletApp', [
  'ngRoute',
  'restletApp.controllers'
]).
config(function($routeProvider, $locationProvider) {
  $routeProvider.
    when('/', {
      templateUrl: 'partials/index.html',
      controller: 'IndexCtrl'
    }).
    when('/list', {
      templateUrl: 'partials/list.html',
      controller: 'ListCtrl'
    }).
    otherwise({
      redirectTo: '/'
    });
  $locationProvider.html5Mode(true);
});