const form = document.getElementById('form')
const errorMessage = document.getElementById('error-message')

const numberYField = document.getElementById('numberYField')
const radiusField = document.getElementById('radius')

var valueX = document.getElementById('hiddenButtonX')
var canvas = document.getElementById('canvas')

$(document).ready(function(){

    $('.coordinatesCanvas').click(function(event){
        let answer = validateR()
        if (answer === '') {
            let pos = $(this).offset();
            let elem_left = pos.left.toFixed(0);
            let elem_top = pos.top.toFixed(0);
            let lambda = this.width / 2 * 0.79;
            let x = (event.pageX - elem_left - this.width / 2) / lambda * radiusField.value;
            let y = -1 * (event.pageY - elem_top - this.height / 2) / lambda * radiusField.value;

            $.get('test', {valueX : x, valueY: y, valueR : radiusField.value, isPIC : true},
                function (data) {
                    drawClick(x, y, radiusField.value, data)
                });

        }
        errorMessage.innerText = answer
    });
});

function buttonClicked(button) {
    valueX.value = button.value
}

function drawCoordinates() {
    coordinates = canvas.getContext('2d')
    var coordinatesImg = new Image(400, 400)
    coordinatesImg.src = "pics/coordinates.png"
    coordinatesImg.onload = function() {
        coordinates.drawImage(coordinatesImg, 0, 0)
    }
}

function drawClick(x, y, r, data) {

        coordinates = canvas.getContext('2d')
        x = parseFloat(x) / parseFloat(r) * (canvas.width / 2 * 0.79);
        y = -1 * (parseFloat(y) / parseFloat(r) * (canvas.width / 2 * 0.79));
        console.log(data)
        if (data == 1) {
            coordinates.fillStyle = "green";
        } else {
            coordinates.fillStyle = "red";
        }
        coordinates.fillRect(canvas.width / 2 + x, canvas.height / 2 + y, 5, 5)
}

function clearCanvas() {
    ctx = canvas.getContext('2d')
    ctx.clearRect(0, 0, 400, 400)
    drawCoordinates()
}

function validateX() {
    if (valueX.value == null) {
        return 'Выберите значение X'
    }
    return ''
}

function validateY() {
    if (numberYField.value === '' || numberYField.value == null) {
        return 'Введите значение Y.'
    } else {
        numberYField.value = numberYField.value.replace(',', '.')

        if (!isNaN(numberYField.value)) {

            let formattedNumber = Number(numberYField.value)

            if (numberYField.value.includes('.')) {

                if (numberYField.value.split('.')[1].length > 2) {
                    formattedNumber = Number(numberYField.value.split('.')[0] + '.' + numberYField.value.split('.')[1].slice(0, 1))
                }
            }

            if (formattedNumber >= 5 || formattedNumber <= -3) {
                return 'Введенное значение Y не в диапазоне.'
            }

        } else {
            return 'Введено некорректное значение Y.'
        }

    }
    return ''
}

function validateR() {
    if (radiusField.value === '' || radiusField.value == null) {
        return 'Введите значение радиуса.'
    } else {
        radiusField.value = radiusField.value.replace(',', '.')
        if (!isNaN(radiusField.value)) {

            let formattedNumber = Number(radiusField.value)

            if (radiusField.value.length > 10) {
                if (radiusField.value[0] === '2' && radiusField.value[1] === '.') {
                    let zerosStr = '';
                    for (let i = 2; i < radiusField.value.length; i++) {
                        if (radiusField.value[i] === '0') {
                            zerosStr += '0'
                        } else {
                            break;
                        }
                    }
                    if (zerosStr.length > 10) {
                        formattedNumber = Number(radiusField.value.replace(zerosStr, ""))
                    }
                }
            } else {
                if (radiusField.value.includes('.')) {
                    if (radiusField.value.split('.')[1].length > 2) {
                        formattedNumber = Number(radiusField.value.split('.')[0] + '.' + radiusField.value.split('.')[1].slice(0, 1))
                    }
                }
            }

            if (formattedNumber >= 5 || formattedNumber <= 2) {
                return 'Радиус не в диапазоне.'
            }

        } else {
            return 'Радиус некорректен.'
        }
    }
    return ''
}

form.addEventListener('submit', (e) => {
    let messages = []

    let answerX = validateX()
    if (answerX !== '') {
        messages.push(answerX)
    }

    let answerY = validateY()
    if (answerY !== '') {
        messages.push(answerY)
        numberYField.style.border = "2px solid red"
    }

    let answerR = validateR()
    if (answerR !== '') {
        messages.push(answerR)
        radiusField.style.border = "2px solid red"
    }

    if (messages.length > 0) {
        e.preventDefault()
        errorMessage.innerText = messages.join('\n\n')
    }

})