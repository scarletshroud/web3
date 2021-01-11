let form = document.getElementById('form');
let valueXInput = document.getElementById('form:valueXInput');
let valueYInput = document.getElementById('form:valueYInput');
let errorMessage = document.getElementById('form:error-message');

function validateX() {
    if (valueXInput.value === null) {
        return 'You should select the value X';
    }
    return '';
}

function validateY() {
    let val = valueYInput.value;

    if (val === '' || val === null) {
        return 'You should select the value Y.';

    } else {
        val = val.replace(',', '.')

        if (!isNaN(val)) {
            let formattedNumber = Number(val)

            if (val.includes('.')) {

                if (val.split('.')[1].length > 2) {
                    formattedNumber = Number(val.split('.')[0] + '.' + val.split('.')[1].slice(0, 1))
                }
            }

            if (!(formattedNumber > -5 && formattedNumber < 3)) {
                return 'Value Y is out of range.'
            }

        } else {
            return 'Value Y is incorrect.'
        }

    }
    return ''
}

function validateR() {
    let valueRInputs = document.getElementsByClassName('form:checkbox-label');
    let checked = 0;

    for (let i = 0; i < valueRInputs.length; i++) {
        if (valueRInputs[i].checked) {
            checked++;
        }
    }

    if (checked === 0) {
        return 'You should select the value R.';
    }

    if (checked > 1) {
        return 'There are more than one the value R selected.'
    }

    return '';
}

function checkboxValidate(value) {
    let checkboxes = document.getElementsByClassName('checkbox');
    for (let checkbox of checkboxes) {
        checkbox.checked = false;
    }
    value.checked = true;
}

function setHiddenValueR(value) {
        let valueR = document.getElementById('hidden-form:valueR-hidden');
        valueR.value = value;
}

function handleRequest() {
    let messages = [];

   /* let answerX = validateX();
    if (answerX !== '') {
        messages.push(answerX);
    } */

    let answerY = validateY();
    if (answerY !== '') {
        messages.push(answerY);
        valueYInput.style.border = "2px solid red";
    }

    let answerR = validateR();
    if (answerR !== '') {
        messages.push(answerR);
    }

    if (messages.length > 0) {
        errorMessage.innerText = messages.join('\n\n');
        return "false";
    }

    return "true";
}