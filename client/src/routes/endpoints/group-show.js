import {bindable, inject} from 'aurelia-framework';
import { DialogService } from 'aurelia-dialog';
import { ViewJsonDialog } from 'routes/dialog/view-json-dialog';

@inject(DialogService)
export class GroupShow {
    @bindable endpoints = null;

    constructor(dialogService) {
        this.dialogService = dialogService;
    }

    columns(endpoint) {
        let columns = []

        if (!endpoint) return columns

        _.forEach(endpoint.urlEntry.response.values, (value) => {
            if (!columns.some(c => c.key === value.key)) {
                columns.push(value)
            }
        })

        return columns
    }

    openJsonBody(json) {
        this.dialogService.open({ viewModel: ViewJsonDialog, model: json, lock: false })
            .whenClosed(response => {
                if (!response.wasCancelled) {}
            });
    }


}