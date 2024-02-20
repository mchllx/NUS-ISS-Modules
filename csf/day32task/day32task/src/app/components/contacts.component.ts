import { Component, Input, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Contact, Friend } from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.css'
})
export class ContactsComponent implements OnInit {

  private fb: FormBuilder = inject(FormBuilder)

  contactForm!: FormGroup
  loadedFriend!: Friend

  @Input()
  name: string="friend"
  email: string="friend@gmail.com"
  phone: string="91234567"
  dob: string="2024-02-14"
  friend: boolean=false
  
  @Output()
  newContact = new Subject<Contact>()

  //lifecycle hook
  ngOnInit(): void {
    this.contactForm = this.createContactForm()
  }

  loadFriend() {
    console.log("loading friend")
    this.name =this.loadedFriend.name
    this.email =this.loadedFriend.email
    this.phone =this.loadedFriend.phone
    this.dob =this.loadedFriend.dob
    this.friend =this.loadedFriend.friend
  }

  processForm() {
    const contact = this.contactForm.value as Contact
    console.log("process form", contact)
    this.newContact.next(contact)
    this.contactForm = this.createContactForm()
  }

  private createContactForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>(this.name, [Validators.required, Validators.minLength(2)]),
      email: this.fb.control<string>(this.email, [Validators.required, Validators.email]),
      phone: this.fb.control<string>(this.phone, [Validators.required, Validators.minLength(8)]),
      dob: this.fb.control<string>(this.dob),
      friend: this.fb.control<boolean>(this.friend)
    })
  }

}
