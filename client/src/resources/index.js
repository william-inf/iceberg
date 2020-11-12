export function configure(config) {
  config.globalResources([
      './value-converters/object-keys',
      './value-converters/group-by',
      './value-converters/json-formatter',
      './value-converters/sort',
      './attributes/bootstrap-tooltip',
      './elements/validatable-field',
      './elements/validatable-select',
      './elements/parent',
      './elements/accordion',
      './elements/are-you-sure-button',
      './elements/page-section',
  ]);
}
