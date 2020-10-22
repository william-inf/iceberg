import { inject, bindable, computedFrom } from 'aurelia-framework';

export class GroupShow {
    @bindable endpoints = null;

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
}