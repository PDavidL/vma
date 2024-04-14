import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from  '@angular/common/http';
import { VehicleDto } from './dto/vehicleDto';
import { Observable } from 'rxjs';
import { CoordinateDto } from './dto/coordinateDto';
import { NotificationDto } from './dto/notificationDto';


@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private baseUrl: string = "http://localhost:8080";
  private allVehiclesUrl: string = "/allVehicles";
  private coordinatesUrl: string = "/vehicleCoordinates"
  private latestNotificationUrl: string = "/latestNotification"
  private vehicleLatestPositionAndNotificationUrl: string = "/vehicleLatestPositionAndNotification"

  constructor(private http: HttpClient) { }

  getAllVehicles(): Observable<VehicleDto[]> {
    return this.http.get<VehicleDto[]>(this.buildUrl(this.allVehiclesUrl));
  }

  getCoordinatesOfVehicle(id: number): Observable<CoordinateDto[]> {
    const params = new HttpParams()
    .set('vehicleId', id);
    return this.http.get<CoordinateDto[]>(this.buildUrl(this.coordinatesUrl), {params});
  }

  getLatestNotification(id: number) : Observable<NotificationDto> {
    const params = new HttpParams()
    .set('vehicleId', id);
    return this.http.get<NotificationDto>(this.buildUrl(this.latestNotificationUrl), {params});
  }

  getVehicleLatestPositionAndNotification(id: number) : Observable<VehicleDto> {
    const params = new HttpParams()
    .set('vehicleId', id);
    return this.http.get<VehicleDto>(this.buildUrl(this.vehicleLatestPositionAndNotificationUrl), {params});
  }

  buildUrl(url : string) : string {
    return this.baseUrl + url;
  }
}
