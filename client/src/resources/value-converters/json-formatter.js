import syntaxHighlight from 'utils/syntax-highlight';

export class JsonFormatterValueConverter {
    toView(v) {
        console.log(v)
        return syntaxHighlight(JSON.stringify(v, null, 4));
    }
}