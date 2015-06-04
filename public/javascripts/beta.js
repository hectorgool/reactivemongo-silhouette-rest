/*
if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}
*/

(function() {

  'use strict';
  var app, dependencies;

  dependencies = ['items.services','slugifier'];

  app = angular.module('items.controller', dependencies);

  app.controller('ItemController', [

    '$scope', 'ItemsPost', '$location', function($scope, ItemsPost, $location) {

      $scope.addItem = function() {

        $scope.jsonItem = {
          name:         $('#name').val(),
          description:  $('#description').val(),
        };

        $console.log('$scope.jsonItem' + $scope.jsonItem);

        ItemsPost.save({}, $scope.jsonItem, (function(response) {
          console.log('Success:' + JSON.stringify(response));
          $scope.jsonResponse = response;
        }), function(errorResponse) {
          console.log('Error:' + JSON.stringify(errorResponse));
        });

      };

    }

  ]);

}).call(this);