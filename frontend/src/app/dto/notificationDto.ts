export class NotificationDto {
    id: number;
    message: string;
    timestamp: Date;

    constructor(id: number, message: string, timestamp: Date) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }
 }