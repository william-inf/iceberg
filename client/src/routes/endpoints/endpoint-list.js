import { inject, bindable } from 'aurelia-framework';

@inject('Iceberg')
export class EndpointList {

    constructor(iceberg) {
        this.iceberg = iceberg;
        this.loading = true;
    }

    attached() {
        this.retrieve();
    }

    retrieve() {
        this.iceberg.getEndpoints()
            .then((json) => {
                this.loading = false;
                this.list = json;
            })
    }

    calcZIndex(idx) {
        let zidx = 1001 + idx;
        return `z-index: ${zidx}`
    }

    get errored() {
        if (!this.list) return 0
        return this.list.filter(x => x.status != 'OK').length
    }

    get ok() {
        if (!this.list) return 0
        return this.list.filter(x => x.status === 'OK').length
    }

}