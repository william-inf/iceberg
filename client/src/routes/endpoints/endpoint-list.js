import { inject } from 'aurelia-framework';

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
                console.log(json);
                this.loading = false;
                this.list = json;
            })
    }

}