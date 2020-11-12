import { bindable } from 'aurelia-framework';

export class AreYouSureButton {
    @bindable affirmativeFunction

    callAffirmative() {
        this.affirmativeFunction()
    }
}