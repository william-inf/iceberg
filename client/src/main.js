// To support async/await
import 'regenerator-runtime/runtime';

import Validation from 'bcx-validation';
import { IcebergService } from './services/iceberg-service';

export function configure(aurelia) {
  aurelia.use.feature('resources');
  aurelia.use.standardConfiguration();
  aurelia.use.plugin('aurelia-animator-css');
  aurelia.use.plugin('bcx-aurelia-reorderable-repeat');
  aurelia.use.plugin('aurelia-combo');
  aurelia.use.plugin('aurelia-dialog', config => {
    config.useDefaults();
    config.useCSS('');
    config.settings.ignoreTransitions = false;
    config.settings.centerHorizontalOnly = true;
  });

  if (process.env.NODE_ENV === 'production') {
    aurelia.use.developmentLogging('warn');
  } else {
    aurelia.use.developmentLogging('info');
    aurelia.use.plugin('aurelia-testing');
  }
  aurelia.start().then(() => aurelia.setRoot());

  aurelia.container.registerTransient('Validation', Validation);
  aurelia.container.registerSingleton('Iceberg', IcebergService);

}
