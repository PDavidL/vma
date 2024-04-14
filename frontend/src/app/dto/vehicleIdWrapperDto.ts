export class VehicleIdWrapperDto {
    vehicles: {id : number}[];

    constructor(vehicles: {id : number}[]) {
        this.vehicles = vehicles;
    }
 }