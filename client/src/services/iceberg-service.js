import _ from 'lodash';
import { HttpClient } from 'aurelia-fetch-client';
import { inject, computedFrom } from 'aurelia-framework';
import {SERVER_URL} from './config.js';

@inject(HttpClient)
export class IcebergService {
    user = null;

    constructor(client) {
        this.client = client;
    }

    @computedFrom('client.isRequesting')
    get isRequesting() {
        return this.client.isRequesting;
    }

    _fetch(url, options = {}) {
        let opts = {};
        _.merge(opts, options);

        return this.client.fetch(url, opts);
    }

    fetch(url, options = {}) {
        console.log('Fetching: ' + SERVER_URL + url);

        return this._fetch(SERVER_URL + url, options)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }

                return response.json().then(
                    json => {
                        if (json.errors) {
                            return Promise.reject(json.errors);
                        }

                        throw new Error(json);
                    },
                    error => {
                        throw new Error(error);
                    }
                );
            });
    }

    postUsingJSON(url, params = {}) {
        return this.fetch(url, {
            method: 'POST',
            body: JSON.stringify(params, null, 2),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    patchUsingJSON(url, params = {}) {
        return this.fetch(url, {
            method: 'PATCH',
            body: JSON.stringify(params, null, 2),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    putUsingJSON(url, params = {}) {
        return this.fetch(url, {
            method: 'PUT',
            body: JSON.stringify(params, null, 2),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    delete(url) {
        return this.fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }

    generateQueryString(payload) {
        const results = [];
        _.forOwn(payload, (value, key) => {
            if (Array.isArray(value)) {
                _.forOwn(value, value => {
                    results.push(`${key}=${value}`);
                });
            } else {
                results.push(`${key}=${value}`);
            }
        });

        return results.join('&');
    }

    getEndpoints() {
        return this.fetch('/api/status')
            .then(json => {
                    return json
                },
                error => {
                    throw error;
                });
    }

    getConfiguration() {
        return this.fetch('/api/settings/config')
            .then(json => {
                    return json
                },
                error => {
                    throw error;
                });
    }

    saveEndpoint(endpoint) {
        return this.postUsingJSON('/api/settings/config/urlEntry', endpoint)
            .then(json => {
                return json
            },
            error => {
                throw error;
            })
    }


    deleteEndpoint(name) {
        return this.delete('/api/settings/config/urlEntry/' + name)
            .then(json => {
                return json
            },
            error => {
                throw error;
            })
    }

}