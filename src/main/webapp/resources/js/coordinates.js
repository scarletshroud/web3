const xmlns = "http://www.w3.org/2000/svg";
const SCALE = 40;

$(document).ready(function(){

    setHiddenValueR(5);
    drawCoordinates(5);

    let flagValue = document.getElementById('hidden-form:redraw-flag');

    $('#canvas').click(function(event) {

        let isRadiusEntered = false;
        let checkboxes = document.getElementsByClassName('checkbox');
        for (let checkbox of checkboxes) {
            if (checkbox.checked === true) {
                isRadiusEntered = true;
            }
        }

        if (isRadiusEntered) {
            let width = 430;
            let height = 430;
            let pos = $(this).offset();

            let elem_left = pos.left.toFixed(0);
            let elem_top = pos.top.toFixed(0);

            let lambda = width / 2;
            let x = (event.pageX - elem_left - 215) / SCALE;
            let y = -1 * (event.pageY - elem_top - 215) / SCALE;

            console.log("x " + x, "y " + y);

            let valueX = document.getElementById('hidden-form:valueX-hidden');
            let valueY = document.getElementById('hidden-form:valueY-hidden');
            let button = document.getElementById('hidden-form:hidden-button');

            valueX.value = x;
            valueY.value = y;

            button.click();
        }
    });
});

function drawCoordinates(value) {
    let quater = document.getElementById('quater');
    let triangle = document.getElementById('triangle');
    let rectangle = document.getElementById('rectangle');

    let radius = value;

    console.log(radius);

    let trVal_1 = 215 - SCALE * radius / 2;
    let trVal_2 = 215 + SCALE * radius / 2;
    triangle.setAttribute('points', "215,"+ trVal_1 + " 215,215 " + trVal_2 + ",215");

    let rectVal = 215 - SCALE * radius / 2;
    let rectWidth = SCALE * radius / 2;
    let rectHeight = SCALE * radius;
    rectangle.setAttribute('x', rectVal);
    rectangle.setAttribute('width', rectWidth);
    rectangle.setAttribute('height', rectHeight);

    let angle = radius / 2 * SCALE;
    quater.setAttribute('d', "M 215 " + trVal_2 + " A " + angle + " " + angle + ", 250, 0, 0, " + trVal_2 +" 215 L 215 215 Z");

    clearPoints();
    drawPoints();
}

function convertXtoXOY(value) {
    return 215 + value * SCALE;
}

function convertYtoXOY(value) {
    return 215 - value * SCALE;
}

function drawPoints() {
    let table = document.getElementById('resultsTable');

    for (let i = 1; i < table.rows.length; i++) {
        let valueX = parseFloat(table.rows[i].cells[0].innerHTML);
        let valueY = parseFloat(table.rows[i].cells[1].innerHTML);

        if (!isNaN(valueX) && !isNaN(valueY)) {
            let x = convertXtoXOY(valueX);
            let y = convertYtoXOY(valueY);

            let valueR = document.getElementById('hidden-form:valueR-hidden');
            let color = checkHit(valueX, valueY, valueR.value) ? "green" : "red";

            let circle = document.createElementNS(xmlns, "circle");
            circle.setAttribute('cx', x);
            circle.setAttribute('cy', y);
            circle.setAttribute('r', 5);
            circle.setAttribute('fill', color);
            circle.setAttribute('class', "circle");
            document.getElementById('canvas').append(circle);
        }
    }
}

function clearPoints() {
    let circles = document.getElementsByClassName('circle');
    console.log(circles);
    for (let i = 0; i < circles.length; i++) {
        circles[i].remove();
    }
}

function checkHit(x, y, r) {
    console.log("x " + x + "y " + y +  "r " + r);
    let b = ((x > -r / 2 && x < 0 && y > -r && y < 0)
        || (x > 0 && y <= 0 && x * x + y * y < r / 2 * r / 2)
        || (x > 0 && y >= 0 && y < r / 2 - x));
    console.log(b);
    return b;
}

