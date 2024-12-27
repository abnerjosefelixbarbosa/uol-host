import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PlayerType } from '../../models/enums/player-type';
import { Router } from '@angular/router';
import { PlayerService } from '../../services/player-service/player-service.service';
import { PlayerResponse } from '../../models/dtos/player-response';
import { catchError, Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ReactiveFormsModule],
templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  form: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private playerService: PlayerService) {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.email, Validators.required, Validators.maxLength(100)]],
      telephone: [''],
      group: ['', Validators.required],
    });
  }

  registerPlayer() {
    let playerResponse: Observable<PlayerResponse>;

    if (this.form.value.group == "justice_league") {
      playerResponse = this.playerService.registerPlayer({
        name: this.form.value.name,
        email: this.form.value.email,
        telephone: this.form.value.telephone,
        playerType: PlayerType.JUSTICE_LEAGUE      
      });
    } else {
      playerResponse = this.playerService.registerPlayer({
        name: this.form.value.name,
        email: this.form.value.email,
        telephone: this.form.value.telephone,
        playerType: PlayerType.AVENGERS    
      });
    }

    playerResponse
    .subscribe({
      next: (value) => {
        console.log(value)
      },
      error: (err) => {
        console.log(err)
      }
    })
    //console.log(this.form.value);
  }

  goToPlayersList() {
    this.router.navigate(['player-list']);
  }
}
