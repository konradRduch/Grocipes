import { Component, OnInit } from '@angular/core';
import { ChartData, ChartOptions } from 'chart.js';

@Component({
  selector: 'app-body-parameters-chart',
  templateUrl: './body-parameters-chart.component.html',
  styleUrls: ['./body-parameters-chart.component.css']
})
export class BodyParametersChartComponent  implements OnInit {
  public chartData: ChartData<'line'> = {
    labels: [], // Będą to daty pomiarów
    datasets: [
      {
        label: 'Weight (kg)',
        data: [],
        borderColor: 'blue',
        fill: false,
      },
      {
        label: 'Abdominal Circumference (cm)',
        data: [],
        borderColor: 'red',
        fill: false,
      },
      {
        label: 'Body Fat Level (%)',
        data: [],
        borderColor: 'green',
        fill: false,
      }
    ]
  };

  public chartOptions: ChartOptions = {
    responsive: true,
    scales: {
      x: {},
      y: {
        beginAtZero: true
      }
    }
  };

  ngOnInit() {
    // Dane do wizualizacji
    const bodyMeasurements = [
      { id: 1, weight: 80.0, height: 185.0, abdominal_circumference: 60.0, body_fat_level: 0.15, physical_activity: "active", measurement_date: "2024-10-24T17:09:42.411", userId: 2 },
      // Dodaj pozostałe dane...
    ];

    // Inicjalizacja danych wykresu
    this.chartData.labels = bodyMeasurements.map(measurement => new Date(measurement.measurement_date).toLocaleDateString());
    this.chartData.datasets[0].data = bodyMeasurements.map(measurement => measurement.weight);
    this.chartData.datasets[1].data = bodyMeasurements.map(measurement => measurement.abdominal_circumference);
    this.chartData.datasets[2].data = bodyMeasurements.map(measurement => measurement.body_fat_level * 100); // Zmiana na %
  }
}