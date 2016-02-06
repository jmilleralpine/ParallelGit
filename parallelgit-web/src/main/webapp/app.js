app = angular.module('parallel', ['ngRoute', 'ngCookies', 'ui.bootstrap', 'ui.ace', 'treeControl', 'ui.tab.scroll', 'ui.bootstrap.contextMenu', 'cgNotify']);

app.config(function(scrollableTabsetConfigProvider){
  scrollableTabsetConfigProvider.setShowTooltips(false);
  scrollableTabsetConfigProvider.setShowDropDown(false);
});
