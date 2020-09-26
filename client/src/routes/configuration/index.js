import { inject } from 'aurelia-framework';

@inject('Iceberg')
export class Index {

    constructor(iceberg) {
        this.iceberg = iceberg;
        this.loading = true;
    }

    attached() {
        this.retrieve();
    }

    retrieve() {
        this.iceberg.getConfiguration()
            .then((json) => {
                console.log(json);
                this.loading = false;
                this.config = json;
            })
    }

}