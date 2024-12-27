import { PlayerType } from "../enums/player-type";

export interface PlayerRequest {
    name: string;
    email: string;
    telephone: string;
    playerType: PlayerType;
}