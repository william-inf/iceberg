import { bindable, inject, computedFrom } from 'aurelia-framework';

@inject('Iceberg')
export class App {

  opened = null;

  constructor(iceberg) {
    this.iceberg = iceberg;
    this.lite = true;

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
        title: 'Dashboard',
        description: 'Check active status pages.',
        nav: true,
        settings : {
          icon: 'fas fa-home'
        },
      },{
        route: 'configuration',
        moduleId: './routes/configuration/index',
        name: 'configuration',
        title: 'Configuration',
        description: 'Manage or set up configuration and settings for app.',
        nav: true,
        settings : {
          icon: 'fas fa-home'
        },
      }
    ]);
  }

  navigateMenu(routeHref) {
    this.opened = null;
    this.router.navigate(routeHref, {});
  }

  toggleLightMode() {
    if (this.lite) {
      document.body.classList.remove('lite')
      this.lite = null
    } else {
      document.body.classList.add('lite')
      this.lite = true
    }
  }

  @computedFrom('router', 'router.currentInstruction')
  get title() {
    return _.get(this.router, 'currentInstruction.config.title');
  }
}
