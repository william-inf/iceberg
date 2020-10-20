import { bindable, bindingMode, computedFrom } from 'aurelia-framework';

export class ValidatableSelect {
    @bindable({ defaultBindingMode: bindingMode.twoWay }) value;
    @bindable values;
    @bindable optionValue;
    @bindable optionKey;
    @bindable errors;
    @bindable label;

    attached() {
        console.log(`Attached: ${this.label} - ${JSON.stringify(this.value)}`)
    }
}