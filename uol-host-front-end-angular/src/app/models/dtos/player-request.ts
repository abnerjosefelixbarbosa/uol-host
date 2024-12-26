import { PlayerType } from "../enums/player-type";

export interface playerRequest {
    name: string;
    email: string;
    telephone: string;
    playerType: PlayerType;
}