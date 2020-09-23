import { inject } from 'aurelia-framework';

@inject('Iceberg')
export class App {

  hide = true;

  constructor(iceberg) {
    this.iceberg = iceberg;
  }

  configureRouter(config, router) {
    this.router = router;
    config.title = "Iceberg";
    config.options.pushState = true;
    config.options.root = '/';

    config.mapUnknownRoutes('not-found.html');
    config.map([
      {
        route: ['', 'home'],
        moduleId: './routes/home/index',
        name: 'home',
        title: 'Home',
        nav: true,
        settings : {
          icon: 'fas fa-home'
        },
      },{
        route: 'configuration',
        moduleId: './routes/configuration/index',
        name: 'configuration',
        title: 'Configuration',
        nav: true,
        settings : {
          icon: 'fas fa-home'
        },
      }
    ]);
  }
}
