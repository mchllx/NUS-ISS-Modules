import { Injectable } from "@angular/core";
import { User } from "./models";

@Injectable({
    providedIn: 'root'
})

export class StorageService {
    saveToLocalStorage(user: User) {
        localStorage.setItem(user.name, JSON.stringify(user))
    }

    getFromLocalStorage(): User | undefined {
        const u = localStorage.getItem('data')

        if (!!u)
            return JSON.parse(u) as User
        return undefined
        // return localStorage.getItem(name)
    }
}