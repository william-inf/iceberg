export function configure(config) {
  config.globalResources([
      './value-converters/object-keys',
      './value-converters/group-by',
      './value-converters/json-formatter',
      './attributes/bootstrap-tooltip',
      './elements/validatable-field',
      './elements/validatable-select',
      './elements/parent',
      './elements/accordion'
  ]);
}
