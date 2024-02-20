import { Component } from '@angular/core';
import { Contact, Friend } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  contacts: Contact[] = []
  friend: Friend[] = []

  newContact(contact: Contact) {
    if (contact.friend == false) {
      console.log("Not a friend") 
    } else {
      this.contacts.push(contact)
    }
  }

  // loadFriend() {
  //     console.log("Loading friend")
  //   }

  loadFriend(contact: Contact) {
    console.log("selected friend", contact.name+contact.email+contact.phone+contact.dob+contact.friend)

    return this.friend = [{
        name: contact.name,
        email: contact.email,
        phone: contact.phone,
        dob: contact.dob,
        friend: contact.friend
      }]

    // this.friend.push(contact)
    // console.log("Retrieving friend", this.friend)
  }

}
