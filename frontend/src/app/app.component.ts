import { Component } from '@angular/core';
import { GoogleMapsModule } from '@angular/google-maps';
import { RouterOutlet } from '@angular/router';
import { HttpService } from './http.service';
import { VehicleDto } from './dto/vehicleDto';
import { EMPTY, catchError } from 'rxjs';
import {HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CoordinateDto } from './dto/coordinateDto';
import { MarkerDto } from './dto/markerDto';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, GoogleMapsModule, HttpClientModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [HttpService]
})
export class AppComponent {
  
  title : string = 'Vehicle Monitoring Application';
  vehicles: VehicleDto[] = []
  actualVehicleCoordinates: CoordinateDto[] = []
  display: any;
  center: google.maps.LatLngLiteral = {
      lat: 47.47581,
      lng: 19.05749
  };
  zoom : number = 15;
  markers: MarkerDto[] = [];

  constructor(private httpService : HttpService) {}

  ngOnInit() {
    this.reloadVehicles();
  }

  reloadVehicles() {
    this.httpService.getAllVehicles().pipe(catchError(() => EMPTY))
    .subscribe(data => {
      this.vehicles = data;
      this.markers = [];
    });
  }

  showOnMap(vehicleId : number) {
    this.markers = [];
    let message : string = "No message to display";
    this.httpService.getLatestNotification(vehicleId).pipe(catchError(() => EMPTY))
    .subscribe(data => message = data.message)

    this.httpService.getCoordinatesOfVehicle(vehicleId).pipe(catchError(() => EMPTY))
    .subscribe(data => {
      this.actualVehicleCoordinates = data;
      let latestLat : number = this.actualVehicleCoordinates[0].latitude;
      let latestLng : number = this.actualVehicleCoordinates[0].longitude;
      this.markers.push({
        position: {
          lat: latestLat,
          lng: latestLng,
        },
        label: {
          color: 'red',
          text: 'Vehicle' + (vehicleId)  + ': ' + (message),
        },
        title: 'Vehicle Marker',
        options: { animation: google.maps.Animation.BOUNCE},
      });
      this.center = {
        lat: this.actualVehicleCoordinates[0].latitude,
        lng: this.actualVehicleCoordinates[0].longitude
    }
    this.changeZoom(15);
    })
  }

  moveMap(event: google.maps.MapMouseEvent) {
      if (event.latLng != null) this.center = (event.latLng.toJSON());
  }

  move(event: google.maps.MapMouseEvent) {
      if (event.latLng != null) this.display = event.latLng.toJSON();
  }

  changeZoom(event: any) {
    this.zoom = event;
  }
}
