// function decodeHTMLEntities(text) {
//   const textArea = document.createElement('textarea');
//   textArea.innerHTML = text;
//   return textArea.value;
// }
//
// const chartDataParse = JSON.parse((decodeHTMLEntities(chartData)));
// console.log(chartDataParse);


const decoded = JSON.parse(new DOMParser().parseFromString(chartData, "text/html").documentElement.textContent);
const stageLabel = [];
const stageCount = [];

decoded.forEach((e, i) => {
  stageLabel.push(decoded[i].stage);
  stageCount.push(decoded[i].projectsCount);
});

const ctx = document.getElementById('myChart');

const myChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: stageLabel,
    datasets: [{
      label: '# of Projects',
      data: stageCount,
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
      ],
      borderWidth: 1
    }]
  },
});

