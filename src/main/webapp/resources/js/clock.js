function startClock() {
    let date = new Date();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    let clock = document.getElementById('clock');
    let hourBlock = document.getElementById('hour');
    let minuteBlock = document.getElementById('minute');
    let secondBlock = document.getElementById('second');

    setTime();

    function setTime() {
        setSecond();
        setMinute();
        setHour();
    }

    function setHour() {
        if (hour > 11) {
            hour -= 12;
        }

        let hourAngle = hour * 30;
        hourBlock.style.transform = "rotate(" + hourAngle + "deg)";

        setTimeout(function () {
            hourAngle += 30;
            hourBlock.style.transform = "rotate(" + hourAngle + "deg)";

            setInterval(function () {
                hourAngle += 30;
                hourBlock.style.transform = "rotate(" + hourAngle + "deg)";
            }, 3600000);
        }, (60 - minute) * 60000);
    }

    function setMinute() {
        let minAngle = minute * 6;
        minuteBlock.style.transform = "rotate(" + minAngle + "deg)";
        setTimeout(function () {
            minAngle += 6;
            minuteBlock.style.transform = "rotate(" + minAngle + "deg)";

            setInterval(function () {
                minAngle += 6;
                minuteBlock.style.transform = "rotate(" + minAngle + "deg)";
            }, 60000);
        }, (60 - second) * 1000);
    }

    function setSecond() {
        let secAngle = second * 6;
        secondBlock.style.transform = "rotate(" + secAngle + "deg)";

        setInterval(function () {
            secAngle += 6;
            secondBlock.style.transform = "rotate(" + secAngle + "deg)";
        }, 1000);
    }
}