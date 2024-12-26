import { PlayerType } from "../enums/player-type";

export interface playerResponse {
    id: string;
    name: string;
    email: string;
    telephone: string;
    playerType: PlayerType;
    codename: string; 
}