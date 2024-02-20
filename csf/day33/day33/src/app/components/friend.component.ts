import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrl: './friend.component.css'
})
export class FriendComponent {

  form: FormGroup = new FormGroup({});

  constructor(private fb: FormBuilder) {
  }
 
  ngOnInit(): void {
    this.form = this.fb.group({
      name: this.fb.control<string>("", [Validators.required])
    })
  }

}
