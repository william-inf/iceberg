import { DialogController } from 'aurelia-dialog';
import { inject,  } from 'aurelia-framework';

@inject(DialogController)
export class ViewJsonDialog {

    constructor(controller) {
        this.controller = controller;
    }

    activate(json) {
        this.model = json;
    }

    get jsonStr() {
        return this.model;
    }

}