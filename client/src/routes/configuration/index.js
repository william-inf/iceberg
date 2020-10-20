import { inject } from 'aurelia-framework';
import { DialogService } from 'aurelia-dialog';
import { AddEndpointDialog } from 'routes/dialog/add-endpoint-dialog';

@inject('Iceberg', DialogService)
export class Index {

    loading = true;

    constructor(iceberg, dialogService) {
        this.iceberg = iceberg;
        this.dialogService = dialogService;
    }

    attached() {
        this.retrieve();
    }

    retrieve() {
        this.iceberg.getConfiguration()
            .then((json) => {
                this.loading = false;
                this.config = json;
            })
    }

    addEndpoint() {
        this.dialogService.open({ viewModel: AddEndpointDialog, model: {}, lock: false })
            .whenClosed(response => {
                if (!response.wasCancelled) {
                    this.loading = true;
                    this.retrieve();
                }
            });
    }
}