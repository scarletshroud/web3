var canvas = document.getElementById('canvas')

function drawCoordinates() {
    coordinates = canvas.getContext('2d')
    var coordinatesImg = new Image(400, 400)
    coordinatesImg.src = "pics/coordinates.png"
    coordinatesImg.onload = function() {
        coordinates.drawImage(coordinatesImg, 0, 0)
        drawPoint()
    }
}

function drawPoint() {
    let valueX = document.getElementById('hiddenResultX')
    let valueY = document.getElementById('hiddenResultY')
    let valueR = document.getElementById('hiddenResultR')
    let inZone = document.getElementById('hiddenInZone')

    if (valueX.value !== '' && valueY.value !== '' && valueR.value !== '' && inZone.value !== '') {
        coordinates = canvas.getContext('2d')
        let x = parseInt(valueX.value) / parseFloat(valueR.value) * (canvas.width / 2 * 0.79);
        let y = -1 * (parseFloat(valueY.value) / parseFloat(valueR.value) * (canvas.width / 2 * 0.79));
        if (inZone.value === 'Точка в зоне.') {
            coordinates.fillStyle = "green";
        } else {
            coordinates.fillStyle = "red";
        }
        coordinates.fillRect(canvas.width / 2 + x, canvas.height / 2 + y, 5, 5)
    }
}