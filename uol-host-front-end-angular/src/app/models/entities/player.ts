import { PlayerType } from "./playerType";

export interface Player {
    id: string;
    name: string;
    email: string;
    telephone: string;
    playerType: PlayerType;
    codename: string; 
}