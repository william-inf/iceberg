import { DialogController } from 'aurelia-dialog';
import { inject, computedFrom } from 'aurelia-framework';
import { watch } from 'aurelia-watch-decorator';

@inject(DialogController, 'Iceberg', 'Validation')
export class AddEndpointDialog {
    model = {
        addr: '',
        path: '',
        name: '',
        group: '',
        authentication: {
            type: ''
        },
        response: {
            type: '',
            values: []
        }
    };

    isProcessing = false;
    triedOnce = false;
    errorMessage = '';

    constructor(controller, iceberg, validation) {
        this.controller = controller;
        this.iceberg = iceberg;
        this.validator = validation.generateValidator({
            addr: 'mandatory',
            path: 'mandatory',
            name: 'mandatory',
            group: 'mandatory',
            authentication: {
                type: ['mandatory', { validate: 'string', minLength: 4 }]
            },
            response: {
                type: ['mandatory', 'notBlank']
            }
        });
    }

    activate(model) {
        this.model = (model ? model : this.model);
        this.authenticationTypes = this.retrieveAuthenticationTypes()
        this.responseTypes = this.retrieveResponseTypes()
    }

    submit() {
        console.log(this.model)
        if (this.isProcessing) return;
        this.triedOnce = true;

        if (this.hasError) return;

        this.isProcessing = true;
        this.iceberg.saveEndpoint(this.model)
            .then(
                (json) => { this.controller.ok('Success!') },
                err => {
                    console.log(JSON.stringify(err))
                    this.errorMessage = JSON.stringify(err)
                }
            )
            .finally(
                () => { this.isProcessing = false });
    }

    canDeactivate() {}

    addResponseValue() {
        this.model.response.values.push({
            key: '',
            label: ''
        })
    }

    removeResponseValue(index) {
        this.model.response.values.splice(index, 1)
    }

    @watch('model.response.type')
    responseTypeHandler() {
        console.log('fired!');
        this.model.response.values = []
    }

    retrieveAuthenticationTypes() {
        return [{
            name: '',
            label: 'Please select'
        },{
            name: 'Unauthenticated',
            label: 'Unauthenticated'
        }]
    }

    retrieveResponseTypes() {
        return [{
            name: '',
            label: 'Please select'
        },{
            name: 'Presence',
            label: 'Presence'
        },{
            name: 'JSON',
            label: 'JSON'
        }]
    }

    @computedFrom('triedOnce', 'model.addr', 'model.path', 'model.name', 'model.group', 'model.authentication.type', 'model.response.type')
    get errors() {
        if (!this.triedOnce) return {};
        console.log(this.validator(this.model))
        return this.validator(this.model) || {};
    }

    @computedFrom('errors')
    get hasError() {
        return !_.isEmpty(this.errors);
    }
}