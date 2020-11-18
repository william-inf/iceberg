import { inject, computedFrom } from 'aurelia-framework';
import { combo } from 'aurelia-combo';
import { DialogService } from 'aurelia-dialog';
import { AddEndpointDialog } from 'routes/dialog/add-endpoint-dialog';

@inject('Iceberg', DialogService)
export class EndpointList {

    active = null;
    list = [];

    constructor(iceberg, dialogService) {
        this.iceberg = iceberg;
        this.dialogService = dialogService;
        this.loading = true;
    }

    attached() {
        this.retrieve();
    }

    retrieve() {
        this.iceberg.getEndpoints()
            .then((json) => {
                console.log(json)
                this.loading = false;
                this.list = _.orderBy(json, 'urlEntry.order', 'asc');
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

    calcZIndex(idx) {
        let zidx = 1001 + idx;
        return `z-index: ${zidx}`
    }

    @computedFrom('list')
    get errored() {
        if (!this.list) return 0;
        return this.list.filter(x => x.status !== 'OK').length;
    }

    @computedFrom('list')
    get ok() {
        if (!this.list) return 0;
        return this.list.filter(x => x.status === 'OK').length;
    }

    @computedFrom('list')
    get total() {
        if (!this.list) return 0;
        return this.list.length;
    }



    @computedFrom('list')
    get groups() {
        if (!this.list) return 0;
        return this.list.map(x => x.group)
            .filter((value, index, self) => self.indexOf(value) === index);
    }

    @combo('right')
    moveRight() {
        let groups = this.groups;

        if (!this.active) {
            if (this.list.length == 0) return;
            this.active = this.groups[0]
            return
        }

        let idx = _.findIndex(groups, (x => x === this.active))
        if (idx === -1) {
            console.log(idx)
            this.active = null;
            return
        }

        idx += 1 // Increment index to next val
        this.active = ((idx >= groups.length) ? this.active : groups[idx])
    }

    @combo('left')
    moveLeft() {
        let groups = this.groups;

        if (!this.active) {
            return
        }

        let idx = _.findIndex(groups, (x => x === this.active))
        if (idx === -1) {
            console.log(idx)
            this.active = null;
            return
        }

        this.active = ((idx > groups.length - 1) ? null : groups[idx - 1])
    }
}