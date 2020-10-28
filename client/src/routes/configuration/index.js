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

    orderChanged(orderedItems, change) {
        /* eslint no-console: 0 */
        console.log('Ordered items: ' + JSON.stringify(orderedItems));
        console.log('Change: ' + JSON.stringify(change));
    }

    retrieve() {
        this.iceberg.getConfiguration()
            .then((json) => {
                this.loading = false;
                this.config = json;
            })
    }

    addEndpoint() {
        this.dialogService.open({ viewModel: AddEndpointDialog, model: null, lock: false })
            .whenClosed(response => {
                if (!response.wasCancelled) {
                    this.loading = true;
                    this.retrieve();
                }
            });
    }
}