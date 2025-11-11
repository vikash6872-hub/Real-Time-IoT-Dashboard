const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

let tempChart, humChart, aqChart;

function createChart(ctx, label) {
  return new Chart(ctx, {
    type: 'line',
    data: { datasets: [{ label, data: [] }]},
    options: {
      animation: false,
      scales: { x: { type: 'time', time: { unit: 'minute' } }, y: { beginAtZero: true } }
    }
  });
}

window.addEventListener('load', () => {
  tempChart = createChart(document.getElementById('tempChart').getContext('2d'), 'Temperature');
  humChart = createChart(document.getElementById('humChart').getContext('2d'), 'Humidity');
  aqChart = createChart(document.getElementById('aqChart').getContext('2d'), 'Air Quality');

  stompClient.connect({}, () => {
    document.getElementById('latest').innerText = 'Connected';
    stompClient.subscribe('/topic/readings', (msg) => {
      const r = JSON.parse(msg.body);
      onReading(r);
    });
  });
});

function onReading(r){
  const text = `${r.deviceId} — ${r.sensorType}: ${r.value} @ ${new Date(r.ts).toLocaleTimeString()}`;
  document.getElementById('latest').innerText = text;
  pushToChart(r);
}

function pushToChart(r){
  const point = { x: new Date(r.ts), y: r.value };
  if(r.sensorType === 'temperature') {
    tempChart.data.datasets[0].data.push(point);
    if(tempChart.data.datasets[0].data.length>100) tempChart.data.datasets[0].data.shift();
    tempChart.update('none');
  } else if(r.sensorType === 'humidity'){
    humChart.data.datasets[0].data.push(point);
    if(humChart.data.datasets[0].data.length>100) humChart.data.datasets[0].data.shift();
    humChart.update('none');
  } else if(r.sensorType === 'air_quality'){
    aqChart.data.datasets[0].data.push(point);
    if(aqChart.data.datasets[0].data.length>100) aqChart.data.datasets[0].data.shift();
    aqChart.update('none');
  }
}
