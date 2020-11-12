import { inject } from 'aurelia-framework';

@inject('Iceberg')
export class BackupTable {

    loading = true;

    constructor(iceberg) {
        this.iceberg = iceberg;
    }

    attached() {
        this.retrieve();
    }

    retrieve() {
        this.iceberg.getBackups()
            .then((json) => {
                this.loading = false;
                this.list = json
            })
    }

}

