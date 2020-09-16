export function configure(config) {
  config.globalResources([
      './value-converters/object-keys',
      './value-converters/group-by',
      './value-converters/json-formatter'
  ]);
}
