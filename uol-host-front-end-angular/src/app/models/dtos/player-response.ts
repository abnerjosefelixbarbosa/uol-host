import { PlayerType } from "../enums/player-type";

export interface PlayerResponse {
    id: string;
    name: string;
    email: string;
    telephone: string;
    playerType: PlayerType;
    codename: string; 
}