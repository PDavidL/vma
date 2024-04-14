export interface MarkerDto {
    position: {
        lat: number;
        lng: number; 
    }
    label: {
        color: string;
        text: string;
    };
    title: string;
    options: {
        animation: google.maps.Animation
    };

}
 