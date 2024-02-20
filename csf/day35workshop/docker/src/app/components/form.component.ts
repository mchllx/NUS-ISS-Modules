import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RouteService } from './route.service';
import { StorageService } from './storage.service';
import { UserStorage } from './user.storage.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit {

  private fb = inject(FormBuilder)
  private router = inject(Router)
  private routeSvc = inject(RouteService)
  private storageSvc = inject(StorageService)
  private userStorageSvc = inject(UserStorage) 

  hasProcessed=false
  form!: FormGroup

  ngOnInit(): void {
    // const u = this.storageSvc.getFromLocalStorage()

    // if (!!u)
    // this.form = this.fb.group({
    //   name: this.fb.control<string>(!!u? u.name: '', Validators.required),
    //   email: this.fb.control<string>(!!u? u.email: '', Validators.required),
    // })

    this.form = this.fb.group({
      name: this.fb.control<string>('', Validators.required),
      email: this.fb.control<string>('', Validators.required),
    })
    // if expression: u? u.name: '', if u? is true, return u.name, else return ''
  }

  process() {
    const value: any = this.form.value
    console.info('>>> value: ', value)
    this.routeSvc.register(value)
      // .then(resp => {
        // this.form.reset()
      this.hasProcessed=true
      this.router.navigate(['/'])
      this.storageSvc.getFromLocalStorage()
      }
  
  }
