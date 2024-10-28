let dateTime = [];
let temperatures = [];
let humidities = [];
let brightnesses = [];

const ctx1 = document.getElementById('temperatureChart').getContext('2d');
const temperatureChart = new Chart(ctx1, {
    type: 'line',
    data: {
        labels: dateTime,
        datasets: [{
            label: 'Temperature (°C)',
            data: temperatures,
            borderColor: '#FD7238',
            backgroundColor: '#FD723870',
            borderWidth: 2,
            fill: false,
            tension: 0.4,
            pointRadius: 1,
            pointBackgroundColor: '#FD7238'
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        animation: false,
        scales: {
            x: {
                display: true,
                grid: {
                    display: false
                },
                ticks: {
                    display: false
                }
            },
            y: {
                title: {
                    display: true,
                    text: 'Temperature (°C)'
                },
                suggestedMin: 15,
                suggestedMax: 40
            }
        }
    }
});

const ctx2 = document.getElementById('humidityChart').getContext('2d');
const humidityChart = new Chart(ctx2, {
    type: 'line',
    data: {
        labels: dateTime,
        datasets: [{
            label: 'Humidity (%)',
            data: humidities,
            borderColor: '#3C91E6',
            backgroundColor: '#3C91E670',
            borderWidth: 2,
            fill: false,
            tension: 0.4,
            pointRadius: 1,
            pointBackgroundColor: '#3C91E6'
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        animation:false,
        scales: {
            x: {
                display: true,
                grid: {
                    display: false
                },
                ticks: {
                    display: false
                }
            },
            y: {
                title: {
                    display: true,
                    text: 'Humidity (%)'
                },
                suggestedMin: 30,
                suggestedMax: 100
            }
        }
    }
});

const ctx3 = document.getElementById('brightnessChart').getContext('2d');
const brightnessChart = new Chart(ctx3, {
    type: 'line',
    data: {
        labels: dateTime,
        datasets: [{
            label: 'Brightness (lx)',
            data: brightnesses,
            borderColor: '#FFCE26',
            backgroundColor: '#FFCE2670',
            borderWidth: 2,
            fill: false,
            tension: 0.4,
            pointRadius: 1,
            pointBackgroundColor: '#FFCE26'
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        animation: false,
        scales: {
            x: {
                display: true,
                grid: {
                    display: false
                },
                ticks: {
                    display: false
                }
            },
            y: {
                title: {
                    display: true,
                    text: 'Brightness (lx)'
                },
                suggestedMin: 50,
                suggestedMax: 350
            }
        }
    }
});

function updateCharts() {
    fetch("/latest-data")
        .then(response => response.json())
        .then(data => {
            dateTime.length = 0;
            temperatures.length = 0;
            humidities.length = 0;
            brightnesses.length = 0;

            data.forEach(entry => {
                dateTime.push(new Date(entry.dateTime).toLocaleTimeString());
                temperatures.push(entry.temperature);
                humidities.push(entry.humidity);
                brightnesses.push(entry.brightness);
            });

            dateTime.reverse();
            temperatures.reverse();
            humidities.reverse();
            brightnesses.reverse();

            if (temperatures.length > 0) {
                document.getElementById('temperature-display').innerText = `${temperatures[temperatures.length - 1]}°C`;
                document.getElementById('humidity-display').innerText = `${humidities[humidities.length - 1]}%`;
                document.getElementById('brightness-display').innerText = `${brightnesses[brightnesses.length - 1]}lx`;
            }

            temperatureChart.update();
            humidityChart.update();
            brightnessChart.update();
        })
        .catch(error => console.error('Error fetching data:', error));
}

// Initial fetch and start periodic update
updateCharts();
setInterval(updateCharts, 2000);

