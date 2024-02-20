import { Injectable } from "@angular/core";
import Dexie from "dexie";
import { User } from "./models";

@Injectable({
    providedIn: 'root'
})

export class UserStorage extends Dexie {

    // create reference
    private user!: Dexie.Table<User, string>

    //initialise dexie, create db 'userdb' if it does not exist
    constructor() {
        super('userdb')

        const COL_USER = 'user'

        // define schema, collection name, PK(from object, email)
        this.version(1).stores({
            // insert index
            // user: 'email'
            // COL_USER: 'email',
            [COL_USER]: 'email'
            // cart: '++cartId', auto incre
        })
        this.user = this.table('user')
    }

    addUser(user: User): Promise<String> {
        // returns promise<pk: string>
        return this.user.add(user)
    }

    getAllUsers(): Promise<User[]> {
        // returns an array of users
        return this.user.toArray()
    }
 
}