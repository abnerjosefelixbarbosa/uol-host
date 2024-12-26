import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { PlayersListComponent } from './pages/players-list/players-list.component';

export const routes: Routes = [
    {
        path: "",
        component: HomeComponent,
    },
    {
        path: "player-list",
        component: PlayersListComponent,
    }
];
