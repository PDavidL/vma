export class VehicleDto {
    id: number;
    positionLat: number;
    positionLng: number;
    notification: string;

    constructor(id: number, positionLat: number, positionLng: number, notification: string) {
        this.id = id;
        this.positionLat = positionLat;
        this.positionLng = positionLng;
        this.notification = notification;
    }
 }