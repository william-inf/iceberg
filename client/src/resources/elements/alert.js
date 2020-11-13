import { bindable } from 'aurelia-framework'

export class Alert {
    @bindable level;
    @bindable dismissable = true;
}