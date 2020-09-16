import { inject, bindable, computedFrom } from 'aurelia-framework';

export class GroupShow {
    @bindable endpoints = null;

    get columns() {
        let columns = []

        if (!this.endpoints) return columns

        _.forEach(this.endpoints, (endpoint) => {
            _.forEach(endpoint.urlEntry.response.values, (value) => {
                if (!columns.some(c => c.key === value.key)) {
                    columns.push(value)
                }
            })
        })

        return columns
    }
}