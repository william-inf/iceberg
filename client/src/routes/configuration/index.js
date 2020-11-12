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
        let order = _.map(orderedItems, (url, i) => {
            return { name: url.name, order: i }
        });

        this.iceberg.orderUrlEntries(order)
            .then(
                (json) => {
                    this.retrieve()
                },
                err => {
                    this.errorMessage = JSON.stringify(err)
                }
            )
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

    editEndpoint(model) {
        this.dialogService.open({ viewModel: AddEndpointDialog, model: model, lock: false })
            .whenClosed(response => {
                if (!response.wasCancelled) {
                    this.loading = true;
                    this.retrieve();
                }
            });
    }

    cloneEndpoint(model) {
        model.name += ' (copy)'
        this.dialogService.open({ viewModel: AddEndpointDialog, model: model, lock: false })
            .whenClosed(response => {
                if (!response.wasCancelled) {
                    this.loading = true;
                    this.retrieve();
                }
            });
    }

    logName(name) {
        console.log(name);
    }

    deleteEndpoint(name) {
        this.iceberg.deleteEndpoint(name)
            .then(
                () => {},
                err => {
                    console.error(JSON.stringify(err))
                }
            )
            .finally(
                () => {
                    this.retrieve()
                });
    }


}