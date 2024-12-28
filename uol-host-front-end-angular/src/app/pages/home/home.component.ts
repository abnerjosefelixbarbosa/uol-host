import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PlayerType } from '../../models/enums/player-type';
import { Router } from '@angular/router';
import { PlayerService } from '../../services/player-service/player-service.service';
import { PlayerResponse } from '../../models/dtos/player-response';
import { catchError, Observable } from 'rxjs';


interface IErrors {
  id: number,
  name: string
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
  errorsName;
  errorsEmail;
  errorsGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private playerService: PlayerService) {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.email, Validators.required, Validators.maxLength(100)]],
      telephone: [''],
      group: ['', Validators.required],
    });

    this.errorsName = new Array<IErrors>();
    this.errorsEmail = new Array<IErrors>();
    this.errorsGroup = new Array<IErrors>();
  }

  registerPlayer() {
    let playerType: PlayerType;

    if (this.form.value.group == "justice_league") {
      playerType = PlayerType.JUSTICE_LEAGUE
    } else {
      playerType = PlayerType.AVENGERS
    }

    this.loadErrors()

    /*
    this.playerService.registerPlayer({
      name: this.form.value.name,
      email: this.form.value.email,
      telephone: this.form.value.telephone,
      playerType: playerType      
    })
    .subscribe({
      next: (value) => {
        console.log(value)
      },
      error: (err) => {
        message = err.message;
      }
    });
    */
  }

  private loadErrors(): void {
    const name = this.form.get('name');
    const email = this.form.get('email');
    const group = this.form.get('group');

    if (!name?.invalid) {
      this.errorsName = [];
    }

    if (!email?.invalid) {
      this.errorsEmail = [];
    }

    if (!group?.invalid) {
      this.errorsEmail = [];
    }

    if (name?.errors!['required'] || email?.errors!['required'] || group?.errors!['required']) {
      this.errorsName.push({id: 1, name: 'nome obrigatório'})
      this.errorsEmail.push({ id: 1, name: 'email obrigatório' })
      this.errorsGroup.push({id: 1, name: 'grupo obrigatório'})
    }
  }

  goToPlayersList() {
    this.router.navigate(['player-list']);
  }

  /*
  getErrorMessages(controlName: string): string[] {
    const control = this.form.get(controlName);
    if (!control || !control.errors) return [];

    const errorMessages: string[] = [];

    if (control.errors['required']) {
      errorMessages.push({'Este campo é obrigatório.'});
    }
    if (control.errors['email']) {
      errorMessages.push('Insira um email válido.');
    }
    if (control.errors['minlength']) {
      errorMessages.push(
        `O comprimento mínimo é ${control.errors['minlength'].requiredLength} caracteres.`
      );
    }
    if (control.errors['maxlength']) {
      errorMessages.push(
        `O comprimento máximo é ${control.errors['maxlength'].requiredLength} caracteres.`
      );
    }

    return errorMessages;
  }
  */  
}
