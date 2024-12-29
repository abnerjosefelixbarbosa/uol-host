import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PlayerType } from '../../models/enums/player-type';
import { Router } from '@angular/router';
import { PlayerService } from '../../services/player-service/player-service.service';
import { PlayerResponse } from '../../models/dtos/player-response';
import { catchError, Observable } from 'rxjs';


interface IErrors {
  id: number,
  description: string
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ReactiveFormsModule],
templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  form: FormGroup;
  messageErrorRequest: string = '';
  messageRequest: string = '';

  constructor(private formBuilder: FormBuilder, private router: Router, private playerService: PlayerService) {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.email, Validators.required, Validators.maxLength(100)]],
      telephone: [''],
      group: ['', Validators.required],
    });
  }

  registerPlayer() {
    let playerType: PlayerType;

    if (this.form.value.group == "justice_league") {
      playerType = PlayerType.JUSTICE_LEAGUE
    } else {
      playerType = PlayerType.AVENGERS
    }

    this.messageErrorRequest = "";
    this.messageRequest = "";

    this.playerService.registerPlayer({
      name: this.form.value.name,
      email: this.form.value.email,
      telephone: this.form.value.telephone,
      playerType: playerType      
    })
    .subscribe({
      next: () => {
        this.messageRequest = "jogador cadastrado"
      },
      error: (err) => {
        this.messageErrorRequest = err.message;
      }
    });
  }

  loadErrorsForm(controleName: string): IErrors[] {
    const name = this.form.get(controleName);

    if (!name || !name.errors) return [];

    const errorsForm = new Array<IErrors>();

    if (name?.errors!['required']) {
      errorsForm.push({
        id: 1,
        description: 'campo obrigatório'
      })
    }

    if (name.errors['email']) {
      errorsForm.push({
        id: 2,
        description: 'email invalido'
      })
    }

    if (name.errors['maxlength']) {
      errorsForm.push({
        id: 3,
        description: `tamanho máximo ${name.errors['maxlength'].requiredLength} caracteres.`
      });
    }

    return errorsForm;
  }

  goToPlayersList() {
    this.router.navigate(['player-list']);
  }
}
